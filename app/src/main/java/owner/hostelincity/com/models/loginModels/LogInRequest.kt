package owner.hostelincity.com.models.loginModels

import com.google.gson.annotations.SerializedName


class LogInRequest {
     var mobile: String? = null
    @SerializedName("password")
     var passwordT: String? = null

    fun setUserdata(mobile: String) {
        this.mobile = mobile
    }

    fun setPassword(password: String) {
        this.passwordT = password
    }

    override fun toString(): String {
        return "LogInRequest(userdata=$mobile, password=$passwordT)"
    }

}
