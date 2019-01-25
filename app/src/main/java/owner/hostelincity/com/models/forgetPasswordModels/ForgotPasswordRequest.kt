package owner.hostelincity.com.models.forgetPasswordModels

class ForgotPasswordRequest{
    var mobile: String? = null

    fun setUserdata(mobile: String) {
        this.mobile = mobile
    }

    override fun toString(): String {
        return "ForgotPasswordRequest(userdata=$mobile)"
    }
}