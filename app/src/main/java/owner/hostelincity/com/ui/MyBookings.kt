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
import kotlinx.android.synthetic.main.activity_my_bookings.*
import owner.hostelincity.com.adapters.MyBookingAdapter
import owner.hostelincity.com.adapters.MyHostelAdapter
import owner.hostelincity.com.models.contactlistModels.GetContactListRequest
import owner.hostelincity.com.models.contactlistModels.GetContactListResponse
import owner.hostelincity.com.models.fullContactDetails.ContactDetailsRequest
import owner.hostelincity.com.models.fullContactDetails.ContactDetailsResponse
import owner.hostelincity.com.models.myBooking.MyBookingData
import owner.hostelincity.com.models.postModels.PostsRequest
import owner.hostelincity.com.models.postModels.PostsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.util.*

class MyBookings : AppCompatActivity() {

    var ownerid = String()
    private val TAG = MyBookings::class.java.getSimpleName()
    private var myBookingData = java.util.ArrayList<MyBookingData>()
    private var myAdapter: MyHostelAdapter? = null
    private var progress: ProgressDialog? = null
    private var Constants = owner.hostelincity.com.commons.Constants()
    private var HostelInCitySource =
        owner.hostelincity.com.retrofit.HostelInCitySource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN ,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_my_bookings)
        setSupportActionBar(toolbar)

        val sp = getSharedPreferences(Constants.PREFERENCE_NAME , 0)
        ownerid = sp.getString(Constants.USER_ID , ownerid)
        val recyclerView = findViewById<RecyclerView>(R.id.myBookingRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this , LinearLayout.VERTICAL , false)

//        myBookingData.add(MyBookingData("Seshu Kusam","Sri Sai Delux PG Mens Hostel","2500","987654321","2","3500"))
//        myBookingData.add(MyBookingData("Nikhil Kumar Singh","Sri Sai Delux PG Mens Hostel","2500","987654321","2","3500"))
//        myBookingData.add(MyBookingData("Moksith raja","Sri Sai Delux PG Mens Hostel","2500","987654321","2","3500"))

        val myAdapter = MyBookingAdapter(myBookingData)
        recyclerView.adapter = myAdapter
        getbookingdetail()
    }

    private fun getbookingdetail() {
        val request = ContactDetailsRequest()
        request.setUserdata(ownerid)
        showLoadingDialog()

        if (Constants.haveInternet(this)) {
            HostelInCitySource.getRestAPI()!!.getFullProfileDetails(request).enqueue(object : Callback<ContactDetailsResponse> {
                override fun onResponse(call: Call<ContactDetailsResponse> , response: Response<ContactDetailsResponse>) {
                    Log.d(TAG , "onResponse2: $response")


                    if (response.isSuccessful()) {
                        getUserBookingList(Objects.requireNonNull<ContactDetailsResponse>(response.body()))
                        dismissLoadingDialog()
                    } else {
                        dismissLoadingDialog()
                        when (response.code()) {
                            404 -> Toast.makeText(this@MyBookings , "not found" , Toast.LENGTH_SHORT).show()
                            500 -> Toast.makeText(this@MyBookings , "server broken" , Toast.LENGTH_SHORT).show()
                            else -> Toast.makeText(
                                this@MyBookings ,
                                R.string.unexpected_error ,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ContactDetailsResponse> , t: Throwable) {
                    t.printStackTrace()
                    dismissLoadingDialog()
                    if (t is SocketTimeoutException) {
                        Toast.makeText(this@MyBookings , "Server internal error try again" , Toast.LENGTH_SHORT)
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

    private fun getUserBookingList(body: ContactDetailsResponse?) {
        val response = body!!.responseCode
        val description = body.description

        when (response) {
            "200" -> {
                myBookingData.clear()
                myBookingData.addAll(body.results!!)
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