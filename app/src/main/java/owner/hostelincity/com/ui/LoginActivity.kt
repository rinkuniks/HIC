package owner.hostelincity.com.ui

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.content_login.*
import owner.hostelincity.com.R
import owner.hostelincity.com.models.loginModels.LogInRequest
import owner.hostelincity.com.models.loginModels.LogInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity() : AppCompatActivity() {

    private val TAG = LoginActivity::class.java.getSimpleName()
    private var progress: ProgressDialog? = null
    private var exit: Boolean? = false
    private var Constants = owner.hostelincity.com.commons.Constants()
    private var HostelInCitySource =
        owner.hostelincity.com.retrofit.HostelInCitySource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN ,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)

        val signUpTextLayout = createAccountTextView
        val forgotPasswordText = forgotPassword
        val passwordEditTextLogIn = passwordEditTextLogIn
        val mobileNumberEditTextLogIn = mobileNumberEditTextLogIn
        val linearLayoutSubmit = linearLayoutSubmit

        //move to Forgot Password Activity
        forgotPasswordText.setOnClickListener{
            val i = Intent(this , ForgotPassword::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_from_right , R.anim.slide_to_left)
        }
        //move to signup Activity
        signUpTextLayout.setOnClickListener{
            val i = Intent(this , SignUpActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_from_right , R.anim.slide_to_left)
        }

        //Login Button OnClick Listener
        linearLayoutSubmit.setOnClickListener{
            val mobile = mobileNumberEditTextLogIn!!.text.toString()
            val password = passwordEditTextLogIn!!.text.toString()

            if (mobile.length > 0) {
                Log.d("" , "")
            } else {
                Snackbar.make(findViewById(android.R.id.content) , "Enter mobile no" , Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mobile.matches(Constants.MobilePattern.toRegex())) {
                Log.d("" , "")
            } else {
                Snackbar.make(findViewById(android.R.id.content) , "Enter valid Mobile number" , Snackbar.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (password.length > 0) {
                Log.d("" , "")
            } else {
                Snackbar.make(findViewById(android.R.id.content) , "Enter password" , Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Constants.haveInternet(applicationContext)) {
                //Toast.makeText(LogIn.this, "processing......", Toast.LENGTH_SHORT).show();
                logInProcess(mobile , password)
            } else {
                Constants.IntenetSettings(this@LoginActivity)
            }
        }
    }

    private fun logInProcess(mobile: String , password: String) {
        showLoadingDialog()

        Log.d("==========>",mobile+" , "+password)

        val request = LogInRequest()
        request.setUserdata(mobile)
        request.setPassword(password)

        Log.d("==>",mobile+" , "+password)

        HostelInCitySource.getRestAPI()!!.userLogin(request).enqueue(object : Callback<LogInResponse> {
            override fun onResponse(call: Call<LogInResponse> , response: Response<LogInResponse>) {
                Log.d(TAG , "onResponse : login $response")
                logInResponse(Objects.requireNonNull<LogInResponse>(response.body()))
                dismissLoadingDialog()
            }

            override fun onFailure(call: Call<LogInResponse> , t: Throwable) {
                Log.d(TAG , "onResponse : login nfcghfghfhgfdhgdhgd")

                t.printStackTrace()
                dismissLoadingDialog()
            }
        })

    }

    private fun logInResponse(body: LogInResponse) {
        val response = body.responseCode
        val description = body.description
        when (response) {
            "200" -> {
                val user_id = body.userId
                val preferences = getSharedPreferences(Constants.PREFERENCE_NAME , 0)
                preferences.edit().putString(Constants.USER_ID , body.userId).apply()
                preferences.edit().putString(Constants.MOBILE_NO , body.mobile).apply()
                preferences.edit().putString(Constants.NAME , body.owner_name).apply()

                Log.d("user_id===in login=>" , user_id + " , , , ,")

                Toast.makeText(this , "Welcome " + body.owner_name , Toast.LENGTH_SHORT).show()
                val i = Intent(this , MainActivity::class.java)
                startActivity(i)
                overridePendingTransition(R.anim.slide_to_right , R.anim.slide_from_left)
            }
            "204" -> Snackbar.make(
                findViewById<View>(android.R.id.content) , description.toString() , Snackbar.LENGTH_LONG
            ).show()
            else -> Snackbar.make(findViewById<View>(android.R.id.content) , description.toString() , Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (exit!!) {
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
            finish() // finish activity
        } else {
            Toast.makeText(this , "Press Back again to Exit." , Toast.LENGTH_SHORT).show()
            exit = true
            Handler().postDelayed({ exit = false } , (2 * 1000).toLong())
        }
    }

    private fun showLoadingDialog() {
        if (progress == null) {
            progress = ProgressDialog(this)
            progress!!.setTitle(R.string.loading_title)
            progress!!.setMessage("Loading......")
        }
        progress!!.show()
        progress!!.setCancelable(false)
    }

    private fun dismissLoadingDialog() {
        if (progress != null && progress!!.isShowing) {
            progress!!.dismiss()
        }
    }

    override// disable keyboard out side the edit text
    fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken , 0)
        }
        return super.dispatchTouchEvent(ev)
    }

}