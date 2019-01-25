package owner.hostelincity.com.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.mybookingrecyclerview.view.*
import owner.hostelincity.com.R
import owner.hostelincity.com.models.myBooking.MyBookingData

class MyBookingAdapter(val bookingDataList : ArrayList<MyBookingData>) : RecyclerView.Adapter<MyBookingAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup , p1: Int): ViewHolder {
        val v= LayoutInflater.from(p0.context).inflate(R.layout.mybookingrecyclerview, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
      return bookingDataList.size
    }

    override fun onBindViewHolder(p0: ViewHolder , p1: Int) {
        val mybookingdata : MyBookingData = bookingDataList[p1]
        p0.nameview.text = mybookingdata.person_name
        p0.hostelNameView.text = mybookingdata.hostel_name
        p0.paidamount.text = mybookingdata.paid_amount
        p0.mobileview.text = mybookingdata.person_mobile
        p0.sharingView.text = mybookingdata.sharing_type
        p0.balanceamount.text = mybookingdata.monthly_rent

    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val nameview = itemView.bookingName
        val hostelNameView = itemView.bookingHostelName
        val paidamount = itemView.bookingPaidAmount
        val mobileview = itemView.bookingMobile
        val sharingView = itemView.bookingSharing
        val balanceamount = itemView.bookingBalanceAmount
    }

}