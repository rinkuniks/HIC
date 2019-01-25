package owner.hostelincity.com.models.diaryModels

import com.google.gson.annotations.SerializedName

class DiaryAddNoteResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var message: String? = null
    var description: String? = null

}
