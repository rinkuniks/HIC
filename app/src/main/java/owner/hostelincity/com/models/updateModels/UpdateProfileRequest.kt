package owner.hostelincity.com.models.updateModels

import com.google.gson.annotations.SerializedName

class UpdateProfileRequest {

    @SerializedName("user_id")
    var userId: String? = null
    var name: String? = null
    var mobile: String? = null
    var location: String? = null
    @SerializedName("photo_name")
    var photoName: String? = null
    @SerializedName("photo_data")
    var photoData: String? = null
}
