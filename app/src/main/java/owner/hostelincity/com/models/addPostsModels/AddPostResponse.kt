package owner.hostelincity.com.models.addPostsModels

import com.google.gson.annotations.SerializedName


class AddPostResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null

}
