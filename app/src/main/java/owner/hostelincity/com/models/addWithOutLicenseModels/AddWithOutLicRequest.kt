package owner.hostelincity.com.models.addWithOutLicenseModels

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class AddWithOutLicRequest {


    @SerializedName("user_id")
    var userId: String? = null
    var location: String? = null
    var date: String? = null
    @SerializedName("from_hrs")
    var fromHrs: String? = null
    @SerializedName("to_hrs")
    var toHrs: String? = null
    var city: String? = null
    @SerializedName("driver_details")
    var driverDetails: ArrayList<String>? = null
    @SerializedName("owner_details")
    var ownerDetails: ArrayList<String>? = null
    @SerializedName("vehicle_number")
    var vehicleNumber: ArrayList<String>? = null
    @SerializedName("vcr_number")
    var vcNumber: ArrayList<String>? = null
    @SerializedName("submitted_to")
    var submittedTo: String? = null


}
