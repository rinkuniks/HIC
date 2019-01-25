package owner.hostelincity.com.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.content_sign_up.*
import android.text.Html
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import owner.hostelincity.com.R
import owner.hostelincity.com.models.signUpModels.SignUpRequest
import owner.hostelincity.com.models.signUpModels.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class SignUpActivity : AppCompatActivity() {

    private var progress: ProgressDialog? = null
    private var HostelInCitySource =
        owner.hostelincity.com.retrofit.HostelInCitySource()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN ,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(owner.hostelincity.com.R.layout.activity_sign_up)
       // setSupportActionBar(toolbar)

        val tv = findViewById<TextView>(owner.hostelincity.com.R.id.checkboxAgree) as TextView
        val text =
            "<font color=#000000>Agree To The</font> " + "<font color=#ff0000>Terms and Condition</font>"
        tv.text = Html.fromHtml(text)

        loginSignupActivity.setOnClickListener {
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        signup.setOnClickListener {

            val mobilenmber = findViewById<EditText>(R.id.input_mobile)
            val password =findViewById<EditText>(R.id.input_password_)
            val username = findViewById<EditText>(R.id.input_name)
            val city = findViewById<EditText>(R.id.input_city)
            val num_hostel =findViewById<EditText>(R.id.input_hostel_no)
            val term = findViewById<CheckBox>(R.id.checkboxAgree)

            if (mobilenmber.length() > 0 ){
                Log.d("" , "")
            }else{
                Toast.makeText(this@SignUpActivity , "Enter Mobile Number" , Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            if(mobilenmber.equals(Constants.MobilePattern)) {
//                Log.d("" , "")
//            } else run {
//                Snackbar.make(findViewById(android.R.id.content) , "Enter valid Mobile number" , Snackbar.LENGTH_SHORT)
//                    .show()
//            }
            if (username.length() > 0){
                Log.d("" , "")
            }else{
                Toast.makeText(this@SignUpActivity , "Enter Username" , Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length() > 0){
                Log.d("" , "")
            }else{
                Toast.makeText(this@SignUpActivity , "Enter Password" , Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (city.length() > 0){
                Log.d("" , "")
            }else{
                Toast.makeText(this@SignUpActivity , "Enter City Name" , Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (num_hostel.length() > 0){
                Log.d("" , "")
            }else{
                Toast.makeText(this@SignUpActivity , "Enter Number of Hostels" , Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!(checkboxAgree.isChecked)){
                Toast.makeText(this,"Check the Terms And Condition",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            sendDataToserver()
        }
    }

    private fun sendDataToserver() {

        val mobile = input_mobile.text.toString()
        val name = input_name.text.toString()
        val password = input_password_.text.toString()
        val term = checkboxAgree.text.toString()
        val city = input_city.text.toString()
        val num_hostels = input_hostel_no.text.toString()

        val request = SignUpRequest()
        request.setUserMobile(mobile)
        request.setName(name)
        request.setUserPassword(password)
        request.setcity(city)
        request.setnum_hostels(num_hostels)
        request.setterms(term)
        showLoadingDialog()

        Log.d("==========>",mobile+" , "+name+" , "+password+", "+term+" , "+city+" , "+num_hostels)
        Log.d("subbu", request.toString())

        HostelInCitySource.getRestAPI()!!.register(request).enqueue(object : Callback<SignUpResponse> {

            override fun onResponse(call: Call<SignUpResponse> , response: Response<SignUpResponse>) {
                Log.d("==========" , "onResponse : login $response")
                signUpResponse(Objects.requireNonNull<SignUpResponse>(response.body()))
                dismissLoadingDialog()
            }

            override fun onFailure(call: Call<SignUpResponse> , t: Throwable) {
                t.printStackTrace()
                dismissLoadingDialog()
            }
        })

    }

    private fun signUpResponse(body: SignUpResponse?) {

        val code = body!!.code
        val mobile = body.mobile
        val i = Intent(applicationContext , OtpVerify::class.java)
        i.putExtra("value" , "2")
        i.putExtra("mobileNoFromSignInResponse" , mobile + "")
        startActivity(i)
        overridePendingTransition(R.anim.slide_from_right , R.anim.slide_to_left)
        if (code.equals("200")){
            Toast.makeText(this,body.description,Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(this,body.description,Toast.LENGTH_LONG).show()
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
        if (progress != null && progress!!.isShowing()) {
            progress!!.dismiss()
        }
    }
}