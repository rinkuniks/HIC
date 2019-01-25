package owner.hostelincity.com.models.groupPostsModels

import com.google.gson.annotations.SerializedName

class GroupPostResults {

    @SerializedName("user_id")
    var userIdOfUser: String? = null
    @SerializedName("userName")
    var name: String? = null
    var userImage: String? = null
    var location: String? = null
    var designation: String? = null
    @SerializedName("post_description")
    var description: String? = null
    @SerializedName("postedOn")
    var daysBeforeDate: String? = null
    @SerializedName("totalComments")
    var noOfComments: String? = null
    @SerializedName("post_image")
    var postImage: String? = null
    @SerializedName("post_id")
    var postId: String? = null
    var years: String? = null
    var months: String? = null
    var days: String? = null
    var hours: String? = null
    var minutes: String? = null
    var seconds: String? = null
}
