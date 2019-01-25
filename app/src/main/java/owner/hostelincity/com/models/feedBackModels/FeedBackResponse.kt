package owner.hostelincity.com.models.feedBackModels

import com.google.gson.annotations.SerializedName

class FeedBackResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var message: String? = null
    var description: String? = null

}
