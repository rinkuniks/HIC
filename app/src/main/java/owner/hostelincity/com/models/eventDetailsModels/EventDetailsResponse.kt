package owner.hostelincity.com.models.eventDetailsModels

import com.google.gson.annotations.SerializedName

class EventDetailsResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null
    @SerializedName("photos")
    var photos: List<EventDetailsPhotos>? = null
    @SerializedName("videos")
    var videos: List<EventDetailsVideos>? = null


}
