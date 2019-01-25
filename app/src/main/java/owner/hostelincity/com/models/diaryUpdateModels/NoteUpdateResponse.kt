package owner.hostelincity.com.models.diaryUpdateModels

import com.google.gson.annotations.SerializedName

class NoteUpdateResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var message: String? = null
    var description: String? = null
}
