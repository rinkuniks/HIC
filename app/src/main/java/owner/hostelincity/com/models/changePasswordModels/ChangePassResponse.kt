package owner.hostelincity.com.models.changePasswordModels

import com.google.gson.annotations.SerializedName

class ChangePassResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var message: String? = null
    var description: String? = null


}
