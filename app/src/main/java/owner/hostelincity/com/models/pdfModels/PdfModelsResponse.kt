package owner.hostelincity.com.models.pdfModels

import com.google.gson.annotations.SerializedName

class PdfModelsResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null

    @SerializedName("results")
    var results: List<PdfModelsResults>? = null
}
