package owner.hostelincity.com.models.otpModels

import com.google.gson.annotations.SerializedName

class OtpRequest {

    private var mobile: String? = null
    private var otp: String? = null
    @SerializedName("refer_code")
    val referCode: String? = null

    fun setOtp(otp: String) {
        this.otp = otp
    }

    fun setMobile(stringExtra: String?) {
        this.mobile = stringExtra
    }
}
