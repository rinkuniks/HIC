package owner.hostelincity.com.models.diaryModels

import com.google.gson.annotations.SerializedName

class DiaryAddNoteRequest {

    @SerializedName("user_id")
    var userId: String? = null
    var title: String? = null
    var description: String? = null
    @SerializedName("date")
    var givenDate: String? = null
}
