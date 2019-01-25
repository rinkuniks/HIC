package owner.hostelincity.com.models.contactlistModels

import com.google.gson.annotations.SerializedName

class GetContactListRequest {
    @SerializedName("user_id")
    var userId: String? = null
}
