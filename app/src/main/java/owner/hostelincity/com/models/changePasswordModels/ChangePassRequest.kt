package owner.hostelincity.com.models.changePasswordModels

import com.google.gson.annotations.SerializedName

class ChangePassRequest {

    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("old_password")
    var oldPassword: String? = null
    @SerializedName("new_password")
    var newPassword: String? = null
    @SerializedName("confirm_password")
    var repeatPassword: String? = null


}
