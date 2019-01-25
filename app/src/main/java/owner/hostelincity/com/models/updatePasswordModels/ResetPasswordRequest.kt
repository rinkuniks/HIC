package owner.hostelincity.com.models.updatePasswordModels

import com.google.gson.annotations.SerializedName

class ResetPasswordRequest {


    private var mobile: String? = null

    @SerializedName("new_password")
    private var newPassword: String? = null

    @SerializedName("confirm_password")
    private var confirmPassword: String? = null

    fun setNewPassword(newPasswordString: String?) {
        newPassword = newPasswordString
    }

    fun setConfirmPassword(repeatNewPasswordString: String?) {
     confirmPassword = repeatNewPasswordString
    }

    fun setMobile(stringExtra: String?) {
        mobile = stringExtra
    }

    override fun toString(): String {
        return "ResetPasswordRequest(newPassword=$newPassword, confirmPassword=$confirmPassword)"
    }

}
