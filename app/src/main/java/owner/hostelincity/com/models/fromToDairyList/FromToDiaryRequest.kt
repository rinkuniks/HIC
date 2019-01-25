package owner.hostelincity.com.models.fromToDairyList

import com.google.gson.annotations.SerializedName

class FromToDiaryRequest {


    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("from_date")
    var fromDate: String? = null
    @SerializedName("to_date")
    var toDate: String? = null
}
