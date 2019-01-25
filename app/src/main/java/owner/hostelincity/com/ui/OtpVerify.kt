package owner.hostelincity.com.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import owner.hostelincity.com.R
import owner.hostelincity.com.commons.IncomingSms
import owner.hostelincity.com.interfaces.SmsListeners
import owner.hostelincity.com.models.otpModels.OtpRequest
import owner.hostelincity.com.models.otpModels.OtpResponse
import owner.hostelincity.com.models.resendOtp.ResendOtpRequest
import owner.hostelincity.com.models.resendOtp.ResendOtpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.regex.Pattern

class OtpVerify : AppCompatActivity() {

    private val TAG = OtpVerify::class.java.simpleName
    private var progress: ProgressDialog? = null
    private var countdownText: TextView? = null
    private var editTextOtp: EditText? = null
    private var mobileNoFromForgotPassword: String? = null
    private var otp: String? = null
    private var value:String? = null
    private val referral: String? = null
    internal val MULTIPLE_PERMISSIONS = 10
    internal var permissions = arrayOf(Manifest.permission.RECEIVE_SMS)
    val Constants = owner.hostelincity.com.commons.Constants()
    private var HostelInCitySource =
        owner.hostelincity.com.retrofit.HostelInCitySource()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)

        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                value = null
            } else {
                value = extras.getString("value")
            }
        } else {
            value = savedInstanceState.getSerializable("value") as String
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.colorAccent)
        }

        checkPermissions()
        //setTimer();
        val mobileNoText = findViewById<TextView>(R.id.mobile_no_text)
        val countdownText = findViewById<TextView>(R.id.countdown_text)
        val buttonSubmitOtp = findViewById<Button>(R.id.button_submit_otp)
        val editTextOtp = findViewById<EditText>(R.id.editTextOtp)
        val resendOtpTextView = findViewById<TextView>(R.id.resendOtpTextView)

        if (value == "1") {
            mobileNoText.setText("+91 " + intent.getStringExtra("mobileNoFromForgotPasswordResponse"))
            mobileNoFromForgotPassword = intent.getStringExtra("mobileNoFromForgotPasswordResponse")

        } else {
            mobileNoText.setText("+91 " + intent.getStringExtra("mobileNoFromSignInResponse"))
        }

        buttonSubmitOtp.setOnClickListener({
            val otp = editTextOtp!!.text.toString().trim { it <= ' ' }
            if (otp == "") {
                Toast.makeText(this@OtpVerify , R.string.please_enter_otp , Toast.LENGTH_SHORT).show()
            } else if (otp.length < 4) {
                Toast.makeText(this@OtpVerify , R.string.please_enter_valid_otp , Toast.LENGTH_SHORT).show()
            } else {
                if (value == "1") {
                    showLoadingDialog()
                    val request = OtpRequest()
                    request.setMobile(intent.getStringExtra("mobileNoFromForgotPasswordResponse"))
                    request.setOtp(otp)
                    if (Constants.haveInternet(applicationContext)) {
                        HostelInCitySource.getRestAPI()!!.verifyOtpForgot(request).enqueue(object : Callback<OtpResponse> {
                            override fun onResponse(call: Call<OtpResponse> , response: Response<OtpResponse>) {
                                Log.d(TAG , "onResponse : $response")
                                otpResponseForgotPassword(Objects.requireNonNull<OtpResponse>(response.body()))
                            }

                            override fun onFailure(call: Call<OtpResponse> , t: Throwable) {
                                t.printStackTrace()
                                dismissLoadingDialog()
                            }
                        })
                    } else {
                        dismissLoadingDialog()
                        Constants.IntenetSettings(this@OtpVerify)
                    }
                } else {
                    showLoadingDialog()
                    val request = OtpRequest()
                    //                        request.setReferCode(referral);
                    request.setMobile(intent.getStringExtra("mobileNoFromSignInResponse"))
                    request.setOtp(otp)
                    if (Constants.haveInternet(applicationContext)) {
                        HostelInCitySource.getRestAPI()!!.verifyOtp(request).enqueue(object : Callback<OtpResponse> {
                            override fun onResponse(call: Call<OtpResponse> , response: Response<OtpResponse>) {
                                Log.d(TAG , "onResponse : $response")
                                otpResponseForSignUp(Objects.requireNonNull<OtpResponse>(response.body()))
                            }

                            override fun onFailure(call: Call<OtpResponse> , t: Throwable) {
                                t.printStackTrace()
                                dismissLoadingDialog()
                            }
                        })
                    } else {
                        dismissLoadingDialog()
                        Constants.IntenetSettings(this@OtpVerify)
                    }
                }
            }
        })

        resendOtpTextView.setOnClickListener({
            if (value == "1") {
                showLoadingDialog()
                val request = ResendOtpRequest()
                request.setMobile(intent.getStringExtra("mobileNoFromForgotPasswordResponse"))

                if (Constants.haveInternet(applicationContext)) {

//                    HostelInCitySource.getRestAPI()!!.resendOtpForgot(request).enqueue(object : Callback<ResendOtpResponse> {
//                        override fun onResponse(call: Call<ResendOtpResponse> , response: Response<ResendOtpResponse>) {
//                            Log.d(TAG , "onResponse : $response")
//                            responseSignUp(Objects.requireNonNull<ResendOtpResponse>(response.body()))
//                        }
//
//                        override fun onFailure(call: Call<ResendOtpResponse> , t: Throwable) {
//                            dismissLoadingDialog()
//                        }
//                    })
                } else {
                    dismissLoadingDialog()
                    Constants.IntenetSettings(this@OtpVerify)
                }
            } else {
                showLoadingDialog()
                val request = ResendOtpRequest()
                request.setMobile(intent.getStringExtra("mobileNoFromSignInResponse"))
                if (Constants.haveInternet(applicationContext)) {
                    HostelInCitySource.getRestAPI()!!.resendOtp(request).enqueue(object : Callback<ResendOtpResponse> {
                        override fun onResponse(call: Call<ResendOtpResponse> , response: Response<ResendOtpResponse>) {
                            Log.d(TAG , "onResponse : $response")
                            responseSignUp(Objects.requireNonNull<ResendOtpResponse>(response.body()))
                        }

                        override fun onFailure(call: Call<ResendOtpResponse> , t: Throwable) {
                            dismissLoadingDialog()
                        }
                    })
                } else {
                    dismissLoadingDialog()
                    Constants.IntenetSettings(this@OtpVerify)
                }
            }
        })
        IncomingSms.bindListener(object : SmsListeners {
            override fun messageReceived(messageText: String) {
                Log.d("Text===>" , messageText)
                val otp = parseCode(messageText)
                if (Constants.haveInternet(applicationContext)) {
                    editTextOtp!!.setText(otp)
                    //                    card_view_login.setVisibility(View.GONE);
                    //                    button_submit_otp.setVisibility(View.GONE);
                    //                    otpVerification(number,otp);
                } else {
                    dismissLoadingDialog()
                    Constants.IntenetSettings(this@OtpVerify)
                    Toast.makeText(this@OtpVerify , "Please check the internet connection" , Toast.LENGTH_SHORT).show()

                }
            }
        })

    }

    private fun parseCode(messageText: String): String {
        Log.d("came to ===>" , "parseCode")
        val p = Pattern.compile("\\b\\d{4}\\b")
        val m = p.matcher(messageText)
        var code = ""
        while (m.find()) {
            Log.d("came to ===>" , "find")
            code = m.group(0)
        }
        return code
    }

    private fun responseSignUp(body: ResendOtpResponse) {
        dismissLoadingDialog()
        val response = body.code
        val description = body.description
        Log.d(TAG , "responseCode   :  $response")
        when (response) {
            "200" -> {
                //countdownText.setVisibility(View.VISIBLE);
                editTextOtp!!.visibility = View.VISIBLE
                //setTimer();
                Snackbar.make(findViewById<View>(android.R.id.content) , description!! , Snackbar.LENGTH_SHORT).show()
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

    private fun otpResponseForSignUp(body: OtpResponse) {
        dismissLoadingDialog()
        val response = body.getCode()
        val description = body.getDescription()

        when (response) {
            "200" -> {
                val preferences = getSharedPreferences(Constants.PREFERENCE_NAME , 0)
                preferences.edit().putString(Constants.USER_ID , body.getUserId()).apply()
                preferences.edit().putString(Constants.MOBILE_NO , body.getMobile()).apply()
                preferences.edit().putString(Constants.NAME , body.getName()).apply()
                preferences.edit().putString(Constants.DESIGNATION , body.getDesignation()).apply()
                preferences.edit().putString(Constants.LOCATION , body.getLocation()).apply()

                Toast.makeText(this , description + " " , Toast.LENGTH_SHORT).show()
                val `in` = Intent(applicationContext , MainActivity::class.java)
                startActivity(`in`)
                finish()
                overridePendingTransition(R.anim.slide_from_right , R.anim.slide_to_left)
            }
            "204" -> {
                Snackbar.make(findViewById<View>(android.R.id.content) , description!! , Snackbar.LENGTH_SHORT).show()
                editTextOtp!!.setText("")
            }
            else -> {
                editTextOtp!!.setText("")
                Snackbar.make(findViewById<View>(android.R.id.content) , description!! , Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun otpResponseForgotPassword(body: OtpResponse) {
        dismissLoadingDialog()
        val response = body.getCode()
        val description = body.getDescription()
        when (response) {
            "200" -> {
                Toast.makeText(this , description + " " , Toast.LENGTH_SHORT).show()
                val i = Intent(applicationContext , UpdatePassword::class.java)
                i.putExtra("forgotPasswordMobile" , mobileNoFromForgotPassword)
                startActivity(i)
                finish()
                overridePendingTransition(R.anim.slide_from_right , R.anim.slide_to_left)
            }
            "204" -> {
                Snackbar.make(findViewById<View>(android.R.id.content) , description!! , Snackbar.LENGTH_SHORT).show()
                editTextOtp!!.setText("")
            }
            else -> {
                editTextOtp!!.setText("")
                Snackbar.make(findViewById<View>(android.R.id.content) , description!! , Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPermissions() {
        var result: Int
        val listPermissionsNeeded = ArrayList<String>()
        for (p in permissions) {
            result = ContextCompat.checkSelfPermission(applicationContext , p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this , listPermissionsNeeded.toTypedArray() , MULTIPLE_PERMISSIONS)
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