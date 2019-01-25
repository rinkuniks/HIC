package owner.hostelincity.com.models.addPostsModels

import com.google.gson.annotations.SerializedName

class AddPostRequest {

    @SerializedName("user_id")
    var userId: String? = null
    var description: String? = null
    var title: String? = null
    @SerializedName("picture_data")
    var pictureData: String? = null
    var picture: String? = null
}
