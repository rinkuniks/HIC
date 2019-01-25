package owner.hostelincity.com.models.fullContactDetails

import com.google.gson.annotations.SerializedName
import owner.hostelincity.com.models.myBooking.MyBookingData

class ContactDetailsResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null
    @SerializedName("owner_id")
    var owner_id: String? = null
    @SerializedName("hostels_details")
    var results: List<MyBookingData>? = null
}
