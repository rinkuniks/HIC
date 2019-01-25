package owner.hostelincity.com.models.postModels

import com.google.gson.annotations.SerializedName

class PostsResults {

    @SerializedName("owner_id")
    var ownerId: String? = null
    @SerializedName("hostel_name")
    var hostelName: String? = null
    @SerializedName("hostel_image")
    var hostelImage: String? = null
    var location: String? = null
    @SerializedName("city")
    var city: String? = null
    @SerializedName("totalViews")
    var views: String? = null
    @SerializedName("Bookings")
    var bookings: String? = null
    @SerializedName("Enquiries")
    var enquiry: String? = null
    @SerializedName("hostel_id")
    var hostelId: String? = null

}