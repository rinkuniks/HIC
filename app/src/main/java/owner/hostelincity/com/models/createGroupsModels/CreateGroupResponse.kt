package owner.hostelincity.com.models.createGroupsModels

import com.google.gson.annotations.SerializedName

class CreateGroupResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null

}
