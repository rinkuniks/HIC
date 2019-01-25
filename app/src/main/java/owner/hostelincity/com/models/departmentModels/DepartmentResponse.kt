package owner.hostelincity.com.models.departmentModels

import com.google.gson.annotations.SerializedName

class DepartmentResponse {

    @SerializedName("code")
    var responseCode: String? = null
    var description: String? = null
    var message: String? = null

    @SerializedName("results")
    var results: List<DepartmentResult>? = null
}
