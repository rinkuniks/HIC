package owner.hostelincity.com.models.accidentInspcationModels

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class AccidentInsRequest {

    @SerializedName("user_id")
    var userId: String? = null
    var location: String? = null
    var date: String? = null
    @SerializedName("registration_number")
    var registrationNumber: String? = null
    @SerializedName("crn_number")
    var crnNumber: String? = null
    var ps: String? = null
    @SerializedName("date1")
    var dateOne: String? = null
    @SerializedName("time1")
    var timeOne: String? = null
    var opinion: String? = null
    var damages: ArrayList<String>? = null
    @SerializedName("road_test")
    var roadTest: String? = null
}
