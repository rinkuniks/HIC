package owner.hostelincity.com.models.postModels

import com.google.gson.annotations.SerializedName
import owner.hostelincity.com.models.myHostels.MyHostelsData

class PostsResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null
    @SerializedName("owner_id")
    var owner_id: String? = null
    @SerializedName("hostels_details")
    var results: List<MyHostelsData>? = null


}
