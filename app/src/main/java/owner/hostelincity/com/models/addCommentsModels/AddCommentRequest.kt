package owner.hostelincity.com.models.addCommentsModels

import com.google.gson.annotations.SerializedName

class AddCommentRequest {

    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("post_id")
    var postId: String? = null
    var comment: String? = null
}
