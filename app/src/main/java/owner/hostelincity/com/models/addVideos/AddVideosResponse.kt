package owner.hostelincity.com.models.addVideos

import com.google.gson.annotations.SerializedName

class AddVideosResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null

}
