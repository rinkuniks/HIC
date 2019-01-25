package owner.hostelincity.com.models.signUpModels

import com.google.gson.annotations.SerializedName

class SignUpRequest {
    fun setUserMobile(s: String) {
        mobile =s
    }
    fun setName(s: String) {
        userName=s
    }
    fun setUserPassword(s: String) {
         password=s
    }
    fun setterms(s: String) {
        terms =s
    }
    fun setcity(s: String) {
        city=s
    }
    fun setnum_hostels(s: String) {
        num_hostels=s
    }
    override fun toString(): String {
        return "SignUpRequest(userName=$userName, mobile=$mobile, password=$password, terms=$terms, city=$city, num_hostels=$num_hostels)"
    }



    @SerializedName("name")
    var userName: String? = null
    var mobile: String? = null
    var password: String? = null
    var terms : String? =null
    var city : String? =null
    var num_hostels : String? =null


}
