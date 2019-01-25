package owner.hostelincity.com.ui

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.content_forgot_password.*
import owner.hostelincity.com.R
import owner.hostelincity.com.models.forgetPasswordModels.ForgotPasswordRequest
import owner.hostelincity.com.models.forgetPasswordModels.ForgotPasswordResponse
import retrofit2.Response
import java.util.*

class ForgotPassword : AppCompatActivity() {

    var HostelInCitySource = owner.hostelincity.com.retrofit.HostelInCitySource()
    var Constants = owner.hostelincity.com.commons.Constants()
    private val TAG = ForgotPassword::class.java.simpleName
    private var progress: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN ,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_forgot_password)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.colorAccent)
        }
        //back Button Forgot Password
        val backLayoutForgot = back_layout_forgot
        backLayoutForgot.setOnClickListener({ onBackPressed() })

        submit_button_forgot_password.setOnClickListener(View.OnClickListener {
           val  mobile = mobile_textView!!.text.toString()

            if (mobile == "") {
                Snackbar.make(
                    findViewById<View>(android.R.id.content) ,
                    R.string.enter_mobile_number ,
                    Snackbar.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            if (mobile.matches(Constants.MobilePattern.toRegex())) {

            } else {
                Snackbar.make(
                    findViewById<View>(android.R.id.content) ,
                    R.string.enter_valid_mobile_number ,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            if (Constants.haveInternet(applicationContext)) {
                validate(mobile);

            } else {
                Constants.IntenetSettings(this@ForgotPassword)
            }
        })
    }

    private fun validate(mobile : String) {
        val request = ForgotPasswordRequest()
        request.setUserdata(mobile)
        showLoadingDialog()

        Log.d("==========>",mobile+"" )

        HostelInCitySource.getRestAPI()!!.forgotPassword(request).enqueue(object : retrofit2.Callback<ForgotPasswordResponse> {
            override fun onResponse(call: retrofit2.Call<ForgotPasswordResponse> , response: Response<ForgotPasswordResponse>) {
                Log.d(TAG , "onResponse : Forgot $response")
                forgotPasswordResponse(Objects.requireNonNull<ForgotPasswordResponse>(response.body()))
                dismissLoadingDialog()
            }

            override fun onFailure(call: retrofit2.Call<ForgotPasswordResponse> , t: Throwable) {
                Log.d(TAG , "onResponse : Forgot nfcghfghfhgfdhgdhgd")

                t.printStackTrace()
                dismissLoadingDialog()
            }
        })
    }

    private fun forgotPasswordResponse(body: ForgotPasswordResponse) {
        dismissLoadingDialog()
        val response = body.code
        val description = body.description

        when (response) {
            "200" -> {
                Toast.makeText(this , description + "" , Toast.LENGTH_SHORT).show()
                val i = Intent(applicationContext , OtpVerify::class.java)
                i.putExtra("value" , "1")
                i.putExtra("mobileNoFromForgotPasswordResponse" , body.mobile)
                startActivity(i)
                finish()
            }
            "204" -> Snackbar.make(
                findViewById<View>(android.R.id.content) ,
                description!! ,
                Snackbar.LENGTH_SHORT
            ).show()
            "301" -> Snackbar.make(
                findViewById<View>(android.R.id.content) ,
                description!! ,
                Snackbar.LENGTH_SHORT
            ).show()
            else -> Snackbar.make(
                findViewById<View>(android.R.id.content) ,
                R.string.unexpected_error ,
                Snackbar.LENGTH_SHORT
            ).show()
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