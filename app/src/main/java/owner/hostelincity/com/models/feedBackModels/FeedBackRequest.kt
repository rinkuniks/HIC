package owner.hostelincity.com.models.feedBackModels

import com.google.gson.annotations.SerializedName

class FeedBackRequest {

    @SerializedName("user_id")
    var userId: String? = null
    var subject: String? = null
    var message: String? = null
}
