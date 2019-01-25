package owner.hostelincity.com.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.get
import kotlinx.android.synthetic.main.myhostelrecyclerview.view.*
import owner.hostelincity.com.R
import owner.hostelincity.com.models.myHostels.MyHostelsData
import owner.hostelincity.com.models.postModels.PostsResults

class MyHostelAdapter (val mContext: Context ,val hostelDataList: ArrayList<MyHostelsData>) : RecyclerView.Adapter<MyHostelAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup , p1: Int): MyHostelAdapter.ViewHolder {
        val v= LayoutInflater.from(p0.context).inflate(R.layout.myhostelrecyclerview, p0, false)
        return MyHostelAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return hostelDataList.size
    }

    override fun onBindViewHolder(p0: MyHostelAdapter.ViewHolder , p1: Int) {
        val myHostelData : MyHostelsData = hostelDataList[p1]
        p0.hostelNameView.text = myHostelData.hostelName
        p0.locationview.text = myHostelData.location
        p0.viewnoview.text = myHostelData.views
        p0.bookingsView.text = myHostelData.bookings
        p0.enquirycount.text = myHostelData.enquiry
        //use this for image
        Picasso.get().load(myHostelData.hostelImage).into(p0.imagesview);


    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val imagesview = itemView.hostelImage
        val hostelNameView = itemView.hostelNameText
        val locationview = itemView.hostelLocation
        val viewnoview = itemView.hostelViewNoCount
        val bookingsView = itemView.hostelBookingCount
        val enquirycount = itemView.hostelEnquiryCount
    }

}