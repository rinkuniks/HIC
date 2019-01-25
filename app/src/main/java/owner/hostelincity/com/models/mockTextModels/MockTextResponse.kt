package owner.hostelincity.com.models.mockTextModels

import com.google.gson.annotations.SerializedName

class MockTextResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null

    @SerializedName("english")
    var resultsEnglish: List<MockTextResults>? = null
    @SerializedName("telugu")
    var resultsTelugu: List<MockTextResults>? = null
}
