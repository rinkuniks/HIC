package owner.hostelincity.com.models.groupsListModels

import com.google.gson.annotations.SerializedName

class GroupListResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null

    @SerializedName("group_list")
    var results: List<GroupListResult>? = null
}
