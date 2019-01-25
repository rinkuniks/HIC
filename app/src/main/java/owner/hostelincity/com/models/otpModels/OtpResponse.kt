package owner.hostelincity.com.models.otpModels

import com.google.gson.annotations.SerializedName

class OtpResponse {

    private var code: String? = null
    var message: String? = null
    internal var description: String? = null
    private var name: String? = null
    private var mobile: String? = null
    var email: String? = null
    @SerializedName("user_id")
    private var userId: String? = null
    private var designation: String? = null
    private var location: String? = null

    fun getLocation(): String? {
        return location
    }

    fun getDesignation(): String? {
        return designation
    }

    fun getName(): String? {
        return name
    }

    fun getMobile(): String? {
    return mobile
    }

    fun getUserId(): String? {
    return userId
    }

    fun getDescription(): String? {
        return description
    }

    fun getCode(): String? {
        return code
    }
}
