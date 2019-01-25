package owner.hostelincity.com.models.postModels

class PostsRequest {

    fun setUserdata(mobile: String) {
        this.owner_id = mobile
    }
    var owner_id: String? = null
}
