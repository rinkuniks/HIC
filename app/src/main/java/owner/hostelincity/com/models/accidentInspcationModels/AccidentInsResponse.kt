package owner.hostelincity.com.models.accidentInspcationModels

import com.google.gson.annotations.SerializedName

class AccidentInsResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null

}
