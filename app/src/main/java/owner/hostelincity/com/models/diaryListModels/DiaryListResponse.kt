package owner.hostelincity.com.models.diaryListModels

import com.google.gson.annotations.SerializedName

class DiaryListResponse {


    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null

    @SerializedName("post_details")
    var results: List<DiaryListResult>? = null

}
