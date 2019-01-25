package owner.hostelincity.com.models.departmentModels

import com.google.gson.annotations.SerializedName

class DepartmentResult {

    var title: String? = null
    var description: String? = null
    var image: String? = null
    @SerializedName("pdf")
    var pdfFile: String? = null
    @SerializedName("pdf_name")
    var pdfName: String? = null
}
