package owner.hostelincity.com.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.content_update_password.*
import owner.hostelincity.com.R
import owner.hostelincity.com.commons.Constants
import owner.hostelincity.com.models.updatePasswordModels.ResetPasswordRequest
import owner.hostelincity.com.models.updatePasswordModels.ResetPasswordResponse
import owner.hostelincity.com.retrofit.HostelInCitySource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.util.*

class UpdatePassword : AppCompatActivity() {

    private val TAG = UpdatePassword::class.java.simpleName
    private var newPassword: EditText? = null
    //private var repeatNewPassword:EditText? = null
    private var progress: ProgressDialog? = null
//    private var newPasswordString: String? = null
//    private var repeatNewPasswordString:String? = null
    private val mobileNoFromDB: String? = null
    private val HostelInCitySource = owner.hostelincity.com.retrofit.HostelInCitySource()
    private val Constants = owner.hostelincity.com.commons.Constants()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN ,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_update_password)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.colorAccent)
        }

        //        SharedPreferences sp = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
        //        mobileNoFromDB = sp.getString(Constants.MOBILE_NO_FORGOT_PASSWORD, mobileNoFromDB);

        val newPassword = findViewById<EditText>(R.id.newPasswordChangePassword)
        val repeatNewPassword = findViewById<EditText>(R.id.repeatNewPassword)

        val submitButtonUpdatePassword = findViewById<Button>(R.id.submitButtonUpdatePassword)

        submitButtonUpdatePassword.setOnClickListener(View.OnClickListener {
            val newPasswordString = newPasswordChangePassword!!.text.toString()
            val repeatNewPasswordString = repeatNewPassword!!.getText().toString()
            if (newPasswordString == "") {
                Snackbar.make(
                    findViewById<View>(android.R.id.content) ,
                    R.string.enter_new_password ,
                    Snackbar.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            if (repeatNewPasswordString == "") {
                Snackbar.make(
                    findViewById<View>(android.R.id.content) ,
                    R.string.enter_repeat_new_password ,
                    Snackbar.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            if (newPasswordString == repeatNewPasswordString) {
                //Toast.makeText(UpdatePassword.this, "Processing.....", Toast.LENGTH_SHORT).show();
                upDatePassword()
            } else {
                Snackbar.make(
                    findViewById<View>(android.R.id.content) ,
                    R.string.both_password_dosent_match ,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

    }


    private fun upDatePassword() {
        showLoadingDialog()
        val newPasswordString = newPasswordChangePassword!!.text.toString()
        val repeatNewPasswordString = repeatNewPassword!!.text.toString()

        val request = ResetPasswordRequest()
        request.setNewPassword(newPasswordString)
        request.setConfirmPassword(repeatNewPasswordString)
        request.setMobile(intent.getStringExtra("forgotPasswordMobile"))

        Log.d("==>",newPasswordString+" , "+repeatNewPasswordString)

        if (Constants.haveInternet(applicationContext)) {
            HostelInCitySource.getRestAPI()!!.updatePassword(request).enqueue(object : Callback<ResetPasswordResponse> {
                override fun onResponse(call: Call<ResetPasswordResponse> , response: Response<ResetPasswordResponse>) {
                    Log.d(TAG , "onResponse: $response")


                    if (response.isSuccessful()) {
                        updatePasswordResponse(Objects.requireNonNull<ResetPasswordResponse>(response.body()))
                        dismissLoadingDialog()
                    } else {
                        dismissLoadingDialog()
                        when (response.code()) {
                            404 -> Toast.makeText(this@UpdatePassword , "not found" , Toast.LENGTH_SHORT).show()
                            500 -> Toast.makeText(this@UpdatePassword , "server broken" , Toast.LENGTH_SHORT).show()
                            else -> Toast.makeText(
                                this@UpdatePassword ,
                                R.string.unexpected_error ,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ResetPasswordResponse> , t: Throwable) {
                    t.printStackTrace()
                    dismissLoadingDialog()
                    if (t is SocketTimeoutException) {
                        Toast.makeText(this@UpdatePassword , "Server internal error try again" , Toast.LENGTH_SHORT)
                            .show()
                        Snackbar.make(
                            findViewById(android.R.id.content) ,
                            "Server internal error try again" ,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        } else {
            dismissLoadingDialog()
            Constants.IntenetSettings(this@UpdatePassword)
        }
    }

    private fun updatePasswordResponse(body: ResetPasswordResponse) {
        val response = body.code
        val description = body.description

        when (response) {
            "200" -> {
                Toast.makeText(this , description + "\n" + " Welcome " + body.name , Toast.LENGTH_SHORT).show()
                val preferences = getSharedPreferences(Constants.PREFERENCE_NAME , 0)
                preferences.edit().putString(Constants.USER_ID , body.userId).apply()
                preferences.edit().putString(Constants.MOBILE_NO , body.mobile).apply()
                preferences.edit().putString(Constants.EMAIL_ID , body.email).apply()
                preferences.edit().putString(Constants.NAME , body.name.toString()).apply()

                val i = Intent(applicationContext , MainActivity::class.java)
                startActivity(i)
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
            else -> Snackbar.make(findViewById<View>(android.R.id.content) , description!! , Snackbar.LENGTH_SHORT).show()
        }

    }


    fun showLoadingDialog() {

        if (progress == null) {
            progress = ProgressDialog(this)
            progress!!.setTitle(R.string.loading_title)
            progress!!.setMessage("Loading......")
        }
        progress!!.show()
        progress!!.setCancelable(false)
    }

    fun dismissLoadingDialog() {

        if (progress != null && progress!!.isShowing) {
            progress!!.dismiss()
        }
    }


}