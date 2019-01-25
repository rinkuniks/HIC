package owner.hostelincity.com.ui

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import owner.hostelincity.com.R
import kotlinx.android.synthetic.main.activity_my_hostels.*
import owner.hostelincity.com.adapters.MyHostelAdapter
import owner.hostelincity.com.models.myHostels.MyHostelsData
import owner.hostelincity.com.models.postModels.PostsRequest
import owner.hostelincity.com.models.postModels.PostsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MyHostels : AppCompatActivity() {
    var ownerid = String()
    private val TAG = MyHostels::class.java.getSimpleName()
    private var myHostelsData = java.util.ArrayList<MyHostelsData>()
    private var myAdapter: MyHostelAdapter? = null
    private var progress: ProgressDialog? = null
    private var Constants = owner.hostelincity.com.commons.Constants()
    private var HostelInCitySource =
        owner.hostelincity.com.retrofit.HostelInCitySource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_my_hostels)
        setSupportActionBar(toolbar)
        setTitle("My Hostels")

        val sp = getSharedPreferences(Constants.PREFERENCE_NAME , 0)
        ownerid =sp.getString(Constants.USER_ID, ownerid )

        val recyclerView = findViewById<RecyclerView>(R.id.myHostelRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false) as RecyclerView.LayoutManager?
        myAdapter = MyHostelAdapter(applicationContext,myHostelsData)
        recyclerView.adapter =myAdapter
        gethosteldetails()
    }

    private fun gethosteldetails() {

        val request = PostsRequest()
        request.setUserdata(ownerid)
        showLoadingDialog()

        if (Constants.haveInternet(this)) {
            HostelInCitySource.getRestAPI()!!.getUserPosts(request).enqueue(object : Callback<PostsResponse> {
                override fun onResponse(call: Call<PostsResponse> , response: Response<PostsResponse>) {
                    Log.d(TAG , "onResponse2: $response")


                    if (response.isSuccessful()) {
                        getUserPostsList(Objects.requireNonNull<PostsResponse>(response.body()))
                        dismissLoadingDialog()
                    } else {
                        dismissLoadingDialog()
                        when (response.code()) {
                            404 -> Toast.makeText(this@MyHostels , "not found" , Toast.LENGTH_SHORT).show()
                            500 -> Toast.makeText(this@MyHostels , "server broken" , Toast.LENGTH_SHORT).show()
                            else -> Toast.makeText(
                                this@MyHostels ,
                                R.string.unexpected_error ,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<PostsResponse> , t: Throwable) {
                    t.printStackTrace()
                    dismissLoadingDialog()
                    if (t is SocketTimeoutException) {
                        Toast.makeText(this@MyHostels , "Server internal error try again" , Toast.LENGTH_SHORT)
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
            Constants.IntenetSettings(this)
        }
    }

    private fun getUserPostsList(body: PostsResponse?) {

        val response = body!!.responseCode
        val description = body.description

        when (response) {
            "200" -> {
                myHostelsData.clear()
                myHostelsData.addAll(body.results!!)
                myAdapter!!.notifyDataSetChanged()

            }

            else -> {
                Toast.makeText(this , description , Toast.LENGTH_SHORT).show()
                Snackbar.make(findViewById<View>(android.R.id.content) , description!! , Snackbar.LENGTH_SHORT).show()
            }


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