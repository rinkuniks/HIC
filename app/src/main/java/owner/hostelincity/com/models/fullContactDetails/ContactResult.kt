package owner.hostelincity.com.models.fullContactDetails

import com.google.gson.annotations.SerializedName

class ContactResult{
    @SerializedName("owner_id")
    var ownerId: String? = null
    @SerializedName("hostel_name")
    var hostelName: String? = null
    @SerializedName("person_name")
    var personName: String? = null
    @SerializedName("person_mobile")
    var mobile: String? = null
    @SerializedName("sharing_type")
    var sharingtype: String? = null
    @SerializedName("monthly_rent")
    var monthlyrent: String? = null
    @SerializedName("paid_amount")
    var paidAmount: String? = null
    @SerializedName("hostel_id")
    var hostelId: String? = null
}