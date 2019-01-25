package owner.hostelincity.com.models.myPostModels

class MyPostsRequest {


    fun setUserdata(mobile: String) {
        this.owner_id = mobile
    }

    var owner_id: String? = null

}
