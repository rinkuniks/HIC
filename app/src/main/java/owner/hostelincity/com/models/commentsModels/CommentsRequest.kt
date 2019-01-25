package owner.hostelincity.com.models.commentsModels

import com.google.gson.annotations.SerializedName

class CommentsRequest {
    @SerializedName("post_id")
    var postId: String? = null
}
