package owner.hostelincity.com.models.pdfModels

import com.google.gson.annotations.SerializedName

class PdfModelsResults {

    @SerializedName("pdf_name")
    var pdfName: String? = null
    @SerializedName("pdf_url")
    var pdfUrl: String? = null
}
