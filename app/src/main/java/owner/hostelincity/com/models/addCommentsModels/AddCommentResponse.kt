package owner.hostelincity.com.models.addCommentsModels

import com.google.gson.annotations.SerializedName

class AddCommentResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null

}
