package owner.hostelincity.com.models.myPostModels

import com.google.gson.annotations.SerializedName

class MyPostsResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null
    var hostel_name: String? = null
    var userImage: String? = null
    var location: String? = null
    var designation: String? = null
    @SerializedName("profile_pic")
    var profilePic: String? = null

    @SerializedName("user_posts")
    var results: List<MyPostsResults>? = null
}
