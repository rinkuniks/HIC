package owner.hostelincity.com.models.commentsModels

import com.google.gson.annotations.SerializedName

class CommentsResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null


    var totalComments: String? = null
    var title: String? = null
    @SerializedName("post_description")
    var postDescription: String? = null
    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("post_image")
    var postImage: String? = null
    var postedBy: String? = null
    var years: String? = null
    var months: String? = null
    var days: String? = null
    var hours: String? = null
    var minutes: String? = null
    var seconds: String? = null
    var designation: String? = null
    var location: String? = null
    @SerializedName("user_status")
    var userStatus: String? = null
    @SerializedName("profile_pic")
    var profilePic: String? = null

    @SerializedName("post_details")
    var results: List<CommentsResults>? = null
}
