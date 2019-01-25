package owner.hostelincity.com.models.contactlistModels

import com.google.gson.annotations.SerializedName
import owner.hostelincity.com.models.myBooking.MyBookingData

class GetContactListResults {

    @SerializedName("code")
    private var responseCode: String? = null
    private var description: String? = null
    private var message: String? = null

    @SerializedName("hostels_details")
    private var results: List<MyBookingData>? = null

}
