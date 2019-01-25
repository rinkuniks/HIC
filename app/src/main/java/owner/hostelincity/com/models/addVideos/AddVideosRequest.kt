package owner.hostelincity.com.models.addVideos

import com.google.gson.annotations.SerializedName

class AddVideosRequest {

    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("video_name")
    var videoName: String? = null
    @SerializedName("video_url")
    var videoUrl: String? = null
}
