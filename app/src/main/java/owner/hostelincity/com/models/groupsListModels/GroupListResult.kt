package owner.hostelincity.com.models.groupsListModels

import com.google.gson.annotations.SerializedName

class GroupListResult {

    @SerializedName("group_id")
    var groupId: String? = null
    @SerializedName("group_name")
    var groupName: String? = null
    @SerializedName("group_pic")
    var groupPic: String? = null
}
