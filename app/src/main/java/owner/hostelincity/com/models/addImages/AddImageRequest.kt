package owner.hostelincity.com.models.addImages

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class AddImageRequest {

    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("picture_data")
    var pictureData: ArrayList<String>? = null
    @SerializedName("picture")
    var pictureName: ArrayList<String>? = null
}
