package owner.hostelincity.com.models.diaryUpdateModels

import com.google.gson.annotations.SerializedName

class NoteUpdateRequest {
    @SerializedName("diary_id")
    var diaryId: String? = null
    var title: String? = null
    var description: String? = null
    @SerializedName("date")
    var dateGiven: String? = null
}
