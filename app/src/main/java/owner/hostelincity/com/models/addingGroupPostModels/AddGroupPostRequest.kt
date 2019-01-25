package owner.hostelincity.com.models.addingGroupPostModels

import com.google.gson.annotations.SerializedName

class AddGroupPostRequest {

    @SerializedName("group_id")
    var groupId: String? = null
    @SerializedName("user_id")
    var userId: String? = null
    var description: String? = null
    var title: String? = null
    @SerializedName("picture_data")
    var pictureData: String? = null
    var picture: String? = null
}
