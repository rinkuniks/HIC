package owner.hostelincity.com.models

import com.google.gson.annotations.SerializedName

class UpdateVersionOfApp {


    @SerializedName("code")
    var responseCode: String? = null
    var message: String? = null
    var description: String? = null
    @SerializedName("couse_number")
    var appVersion: String? = null
}
