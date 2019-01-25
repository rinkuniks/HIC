package owner.hostelincity.com.ui

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import owner.hostelincity.com.R
import owner.hostelincity.com.adapters.DashBoardAdapter
import owner.hostelincity.com.commons.RecyclerTouchListen
import owner.hostelincity.com.interfaces.ClickListener
import owner.hostelincity.com.models.DashBoardModel
import java.util.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener ,
    RecyclerView.OnItemTouchListener {

    var username = String()
    var userId = String()
    val clicklistener: ClickListener? = null
    val gestureDetector: GestureDetector? = null
    private var progress: ProgressDialog? = null
    private var Constants = owner.hostelincity.com.commons.Constants()

    override fun onTouchEvent(p0: RecyclerView , p1: MotionEvent) {
    }

    override fun onInterceptTouchEvent(p0: RecyclerView , p1: MotionEvent): Boolean {
        val child = p0.findChildViewUnder(p1.x , p1.y)
        if (child != null && clicklistener != null && gestureDetector!!.onTouchEvent(p1)) {
            clicklistener.onClick(child , p0.getChildAdapterPosition(child))
        }
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(p0: Boolean) {
    }

    val dashBoardModelList = ArrayList<DashBoardModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN ,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val usernamedisplay = dashboardUsername
        val sp = getSharedPreferences(Constants.PREFERENCE_NAME , Context.MODE_PRIVATE)
        username =sp.getString(Constants.NAME, username )
        userId = sp.getString(Constants.USER_ID , userId)
        usernamedisplay.text = username

        if (userId == "") run {
            val i = Intent(this@MainActivity , LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        val toggle = ActionBarDrawerToggle(
            this , drawer_layout , toolbar , R.string.navigation_drawer_open , R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val numberOfColumns = 2
        dashBoardRecyclerView.layoutManager = GridLayoutManager(this , numberOfColumns)
        val mAdaptor = DashBoardAdapter(dashBoardModelList)
        dashBoardRecyclerView.adapter = mAdaptor

        prepareDashBoardList()

// Click and Move to other activity from Recycler view
        dashBoardRecyclerView.addOnItemTouchListener(
            RecyclerTouchListen(this ,
                dashBoardRecyclerView , object : ClickListener {

                   override fun onClick(view: View , position: Int) {
                        Log.d("position=====>" , position.toString() + "")

                        if (position == 0) {
                        val i = Intent(this@MainActivity , MyHostels::class.java)
                       startActivity(i)
                        overridePendingTransition(R.anim.slide_from_right , R.anim.slide_to_left)
                        }
                        if (position == 1) {
                        val i = Intent(this@MainActivity , MyBookings::class.java)
                        startActivity(i)
                        overridePendingTransition(R.anim.slide_from_right , R.anim.slide_to_left)
                        }
                       if (position == 2) {
                          Toast.makeText(this@MainActivity,"Not Listed",Toast.LENGTH_SHORT).show()
                       }
                       if (position == 3) {
                           Toast.makeText(this@MainActivity,"Not Listed",Toast.LENGTH_SHORT).show()
                       }
                       if (position == 4) {
                           Toast.makeText(this@MainActivity,"Not Listed",Toast.LENGTH_SHORT).show()
                       }
                       if (position == 5) {
                           Toast.makeText(this@MainActivity,"Not Listed",Toast.LENGTH_SHORT).show()
                       }
                    }

                    override fun onLongClick(view: View , position: Int) {}
                })
        )
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {

            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.logout -> {
                logOutProcess()
            }
            R.id.nav_share -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true


    }

    private fun prepareDashBoardList() {

        var list = DashBoardModel("My Hostels" , R.drawable.hostel_svg , "5")
        dashBoardModelList.add(list)

        list = DashBoardModel("My Booking" , R.drawable.my_booking , "50")
        dashBoardModelList.add(list)

        list = DashBoardModel("Hostel Enquiry" , R.drawable.hostel_enquiry , "100")
        dashBoardModelList.add(list)

        list = DashBoardModel("Hostel Views" , R.drawable.hostel_views , "500")
        dashBoardModelList.add(list)

        list = DashBoardModel("Notifications" , R.drawable.notifications , "150")
        dashBoardModelList.add(list)

        list = DashBoardModel("Support" , R.drawable.support , "")
        dashBoardModelList.add(list)
    }

    private fun logOutProcess() {
        showLoadingDialog()
        val preferences = getSharedPreferences(Constants.PREFERENCE_NAME , Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
        val handler = Handler()
        handler.postDelayed({
            // Do something after 5s = 5000ms
            val i = Intent(applicationContext , LoginActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            finishAffinity()
            dismissLoadingDialog()
            startActivity(i)
            //finish();
        } , 2000)
    }

    private fun dismissLoadingDialog() {
        if (progress != null && progress!!.isShowing) {
            progress!!.dismiss()
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

}