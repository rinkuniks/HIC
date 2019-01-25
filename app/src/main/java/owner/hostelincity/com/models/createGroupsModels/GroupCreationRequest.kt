package owner.hostelincity.com.models.createGroupsModels

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class GroupCreationRequest {
    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("group_name")
    var groupName: String? = null
    var members: ArrayList<String>? = null
    @SerializedName("picture_data")
    var pictureData: String? = null
    var picture: String? = null
}
