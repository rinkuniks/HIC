package owner.hostelincity.com.models.updateModels

import com.google.gson.annotations.SerializedName

class UpdateProfileResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var message: String? = null
    var description: String? = null
    var mobile: String? = null
    var name: String? = null
    @SerializedName("profile_pic")
    var profilePic: String? = null
    var location: String? = null


}
