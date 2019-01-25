package owner.hostelincity.com.models.resendOtp

class ResendOtpResponse {

    var code: String? = null
    var message: String? = null
    var description: String? = null
    var mobile: String? = null

    fun getcode(): String? {
        return code
    }

    fun getdescription(): String? {
        return description
    }

}
