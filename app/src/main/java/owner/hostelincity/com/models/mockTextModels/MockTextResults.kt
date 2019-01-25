package owner.hostelincity.com.models.mockTextModels

import com.google.gson.annotations.SerializedName

class MockTextResults {

    @SerializedName("question_id")
    var questionId: String? = null
    var question: String? = null
    var answer: String? = null
    @SerializedName("written_on")
    var writtenOn: String? = null
    var status: String? = null
    @SerializedName("option1")
    var optionOne: String? = null
    @SerializedName("option2")
    var optionTwo: String? = null
    @SerializedName("option3")
    var optionThree: String? = null
    @SerializedName("option4")
    var optionFour: String? = null
    @SerializedName("option5")
    var optionFive: String? = null
    @SerializedName("related_img")
    var relatedImg: String? = null
}
