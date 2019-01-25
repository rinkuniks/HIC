package owner.hostelincity.com.models.pdfModels

import com.google.gson.annotations.SerializedName

class PdfModelsRequest {
    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("form_id")
    var formId: String? = null
}
