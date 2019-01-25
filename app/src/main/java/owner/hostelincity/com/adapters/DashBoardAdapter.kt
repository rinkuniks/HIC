package owner.hostelincity.com.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import owner.hostelincity.com.R
import owner.hostelincity.com.models.DashBoardModel

class DashBoardAdapter(val dashboard : ArrayList<DashBoardModel>) : RecyclerView.Adapter<DashBoardAdapter.MyViewHolder>()
{

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.dashboardrecyclerview,p0,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
            return dashboard.size
        }

    override fun onBindViewHolder(p0: MyViewHolder , p1: Int ) {
        val dash_ = dashboard[p1]
        p0.textViewDashBoard.text = dash_.textView
        p0.textViewNumberDashBoard.text = dash_.textViewnumber
        p0.imageViewDashBoard.setImageResource(dash_.images)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewDashBoard = itemView.findViewById<ImageView>(R.id.image_view_dash_board)
        var textViewDashBoard = itemView.findViewById<TextView>(R.id.text_view_dash_board)
        var textViewNumberDashBoard = itemView.findViewById<TextView>(R.id.text_view_number_dash_board)

    }
}