package owner.hostelincity.com.models.commentsModels

import com.google.gson.annotations.SerializedName

class CommentsResults {


    @SerializedName("comment_id;")
    var commentId: String? = null
    @SerializedName("user_id")
    var userId: String? = null
    var name: String? = null
    var comment: String? = null
    @SerializedName("profile_pic")
    var profilePic: String? = null
    var designation: String? = null
    var location: String? = null
    var years: String? = null
    var months: String? = null
    var days: String? = null
    var hours: String? = null
    var minutes: String? = null
    var seconds: String? = null

}
