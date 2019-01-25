package owner.hostelincity.com.interfaces

import owner.hostelincity.com.commons.Urls
import owner.hostelincity.com.models.forgetPasswordModels.ForgotPasswordRequest
import owner.hostelincity.com.models.forgetPasswordModels.ForgotPasswordResponse
import owner.hostelincity.com.models.fullContactDetails.ContactDetailsRequest
import owner.hostelincity.com.models.fullContactDetails.ContactDetailsResponse
import owner.hostelincity.com.models.loginModels.LogInRequest
import owner.hostelincity.com.models.loginModels.LogInResponse
import owner.hostelincity.com.models.otpModels.OtpRequest
import owner.hostelincity.com.models.otpModels.OtpResponse
import owner.hostelincity.com.models.postModels.PostsRequest
import owner.hostelincity.com.models.postModels.PostsResponse
import owner.hostelincity.com.models.resendOtp.ResendOtpRequest
import owner.hostelincity.com.models.resendOtp.ResendOtpResponse
import owner.hostelincity.com.models.signUpModels.SignUpRequest
import owner.hostelincity.com.models.signUpModels.SignUpResponse
import owner.hostelincity.com.models.updatePasswordModels.ResetPasswordRequest
import owner.hostelincity.com.models.updatePasswordModels.ResetPasswordResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface HostelInCityApi {

//    @get:GET(Urls.GET_USERS_DATA)
//    val contacts: Call<GetContactListResponse>

//    @get:GET(Urls.GET_PHOTOS_LIST)
//    val photos: Call<EventDetailsResponse>
//
//    @get:GET(Urls.GET_DEPARTMENT_LIST)
//    val departmentList: Call<DepartmentResponse>
//
//    @get:GET(Urls.GET_MOCK_TEST_LIST)
//    val mockTextListList: Call<MockTextResponse>


//    @get:GET(Urls.UPDATE_NO)
//    val currentVersion: Call<UpdateVersionOfApp>


    @POST(Urls.LOGIN)
    fun userLogin(@Body request: LogInRequest): Call<LogInResponse>

//    @POST(Urls.CHANGE_PWD)
//    fun changePassword(@Body request: ChangePassRequest): Call<ChangePassResponse>

    @POST(Urls.SIGN_UP)
    fun register(@Body request: SignUpRequest): Call<SignUpResponse>

    @POST(Urls.OTP_SIGN_UP)
    fun verifyOtp(@Body request: OtpRequest): Call<OtpResponse>

    @POST(Urls.RESEND_OTP_)
    fun resendOtp(@Body request: ResendOtpRequest): Call<ResendOtpResponse>

//    @POST(Urls.RESEND_OTP_FORGOT)
//    fun resendOtpForgot(@Body request: ResendOtpRequest): Call<ResendOtpResponse>

    @POST(Urls.FORGOT_PASSWORD)
    fun forgotPassword(@Body request: ForgotPasswordRequest): Call<ForgotPasswordResponse>

    @POST(Urls.RESET_PASSWORD)
    fun updatePassword(@Body request: ResetPasswordRequest): Call<ResetPasswordResponse>

    @POST(Urls.VERIFY_OTP_FOR_FORGOT_PASSWORD)
    fun verifyOtpForgot(@Body request: OtpRequest): Call<OtpResponse>

    @POST(Urls.GET_CONTACT_FULL_DETAILS)
    fun getFullProfileDetails(@Body request: ContactDetailsRequest): Call<ContactDetailsResponse>

    @POST(Urls.GET_ALL_USER_POSTS)
    fun getUserPosts(@Body request: PostsRequest): Call<PostsResponse>

//    //
//    @POST(Urls.GET_SPECIFIC_USER_POSTS)
//    fun getSpecificUserPosts(@Body request: MyPostsRequest): Call<MyPostsResponse>
//
//    @POST(Urls.GET_CONTACT_LIST)
//    fun getContactsList(@Body request: GetContactListRequest): Call<GetContactListResponse>
//
//    @POST(Urls.GET_COMMENT_LIST)
//    fun getCommentsList(@Body request: CommentsRequest): Call<CommentsResponse>
//
//    @POST(Urls.GROUP_LIST)
//    fun groupsList(@Body request: GroupListRequest): Call<GroupListResponse>
//
//    @POST(Urls.GET_POSTS_LIST_DIARY)
//    fun getNotesListDiary(@Body request: DiaryListRequest): Call<DiaryListResponse>
//
//    @POST(Urls.ADD_POST_DIARY)
//    fun setNoteDiary(@Body request: DiaryAddNoteRequest): Call<DiaryAddNoteResponse>
//
//    @POST(Urls.GET_NOTE_DATA_DIARY)
//    fun getNoteDataDiary(@Body request: DiaryGetDataRequest): Call<DiaryGetDataResponse>
//
//    @POST(Urls.UPDATE_POST_DETAILS_DIARY)
//    fun noteUpdateRequest(@Body request: NoteUpdateRequest): Call<NoteUpdateResponse>
//
//    @POST(Urls.ADD_POST_POST)
//    fun addPosts(@Body request: AddPostRequest): Call<AddPostResponse>
//
//    @POST(Urls.ADD_GROUP_POST_POST)
//    fun addGroupPosts(@Body request: AddGroupPostRequest): Call<AddGroupPostResponse>
//
//    @POST(Urls.CREATE_GROUP)
//    fun createGroup(@Body request: GroupCreationRequest): Call<CreateGroupResponse>
//
//    @POST(Urls.ADD_COMMENT)
//    fun getCommentResponse(@Body request: AddCommentRequest): Call<AddCommentResponse>
//
//    @POST(Urls.UPDATE_PROFILE)
//    fun updateProfile(@Body request: UpdateProfileRequest): Call<UpdateProfileResponse>
//
//    @POST(Urls.GROUP_POSTS)
//    fun getGroupPosts(@Body request: GroupPostRequest): Call<GroupPostResponse>
//
//    @POST(Urls.SUBMIT_ACC_INS_FORM)
//    fun addAccInsFormPosts(@Body request: AccidentInsRequest): Call<AccidentInsResponse>
//
//    @POST(Urls.DIARY_LIST_FROM_TO)
//    fun getFromToDataList(@Body request: FromToDiaryRequest): Call<FromToDiaryResponse>
//
//    @POST(Urls.DRIVEN_DRUNK_FORM_UPDATE)
//    fun addDrunkenForm(@Body request: AddDrunkenFormRequest): Call<AddDrunkenFormResponse>
//
//    @POST(Urls.WITHOUT_LICENSE_FORM_UPDATE)
//    fun addWithOutLicForm(@Body request: AddWithOutLicRequest): Call<AddWithOutLicResponse>
//
//    @POST(Urls.PDFS_DATA_LIST)
//    fun pdfsData(@Body request: PdfModelsRequest): Call<PdfModelsResponse>
//
//    @POST(Urls.ADD_IMAGE)
//    fun addImage(@Body request: AddImageRequest): Call<AddImageResponse>
//
//    @POST(Urls.ADD_VIDEO)
//    fun addVideo(@Body request: AddVideosRequest): Call<AddVideosResponse>
//
//    @POST(Urls.FEED_BACK)
//    fun feedBack(@Body request: FeedBackRequest): Call<FeedBackResponse>

}
