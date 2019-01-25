package owner.hostelincity.com.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import owner.hostelincity.com.commons.Urls
import owner.hostelincity.com.interfaces.HostelInCityApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

class HostelInCitySource {
     internal var restAPI: HostelInCityApi? = null

    private fun init() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10 , TimeUnit.SECONDS)
            .writeTimeout(10 , TimeUnit.SECONDS)
            .readTimeout(30 , TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Urls.BASE_URL_HIC)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
            .build()

        restAPI = retrofit.create(HostelInCityApi::class.java)
    }

    fun getRestAPI(): HostelInCityApi? {
        if (restAPI == null) init()
        return restAPI
    }
}
