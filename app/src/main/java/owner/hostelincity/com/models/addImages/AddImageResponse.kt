package owner.hostelincity.com.models.addImages

import com.google.gson.annotations.SerializedName

class AddImageResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null
}
