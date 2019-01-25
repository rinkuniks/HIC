package owner.hostelincity.com.models.diaryListModels

import com.google.gson.annotations.SerializedName

class DiaryListResult {

    @SerializedName("diary_id")
    var noteId: String? = null
    @SerializedName("written_on")
    var noteDate: String? = null
    @SerializedName("description")
    var startingLine: String? = null
    var title: String? = null
    var day: String? = null
    var month: String? = null
    var year: String? = null
}
