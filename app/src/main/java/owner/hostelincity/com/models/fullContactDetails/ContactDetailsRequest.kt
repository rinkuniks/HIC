package owner.hostelincity.com.models.fullContactDetails

import com.google.gson.annotations.SerializedName

class ContactDetailsRequest {

    fun setUserdata(mobile: String) {
        this.owner_id = mobile
    }
    var owner_id: String? = null
}
