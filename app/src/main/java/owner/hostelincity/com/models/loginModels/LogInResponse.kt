package owner.hostelincity.com.models.loginModels

import com.google.gson.annotations.SerializedName

class LogInResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null
    @SerializedName("owner_id")
    var userId: String? = null
    var owner_name: String? = null
    var mobile: String? = null
//    var designation: String? = null
//    var location: String? = null
}
