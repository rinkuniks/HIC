package owner.hostelincity.com.models.groupPostsModels

import com.google.gson.annotations.SerializedName


class GroupPostResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null

//    @SerializedName("post_details")
//    var results: List<PostsResults>? = null
}
