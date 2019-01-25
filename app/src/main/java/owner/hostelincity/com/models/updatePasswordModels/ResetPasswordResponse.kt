package owner.hostelincity.com.models.updatePasswordModels

import com.google.gson.annotations.SerializedName

class ResetPasswordResponse {


     var code: String? = null
    var message: String? = null
     var description: String? = null
    @SerializedName("institute_id")
     var userId: String? = null
    @SerializedName("institute_name")
    var name: String? = null
    @SerializedName("institute_email")
     var email: String? = null
    @SerializedName("institute_mobile")
     var mobile: String? = null

//    fun getCode(): String? {
//        return code
//    }
//
//    fun getDescription(): String? {
//        return description
//    }
//
//    fun getName(): Any? {
//        return name
//    }
//
//    fun getUserId(): String? {
//        return userId
//    }
//
//    fun getMobile(): String? {
//        return mobile
//    }
//
//    fun getEmail(): String? {
//        return email
//    }

}
