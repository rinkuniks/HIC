package owner.hostelincity.com.models.diaryGetData

import com.google.gson.annotations.SerializedName

class DiaryGetDataResponse {


    @SerializedName("code")
    var responseCode: String? = null
    var message: String? = null
    var description: String? = null
    var title: String? = null
    @SerializedName("diary_description")
    var descriptionData: String? = null
    @SerializedName("dayname")
    var dayName: String? = null
    @SerializedName("written_on")
    var writtenOn: String? = null
}
