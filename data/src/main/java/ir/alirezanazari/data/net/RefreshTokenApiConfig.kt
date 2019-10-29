package ir.alirezanazari.data.net


import ir.alirezanazari.data.model.RefreshTokenBodyModel
import ir.alirezanazari.data.model.RefreshTokenModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


class RefreshTokenApiConfig {

    companion object {

        private const val BASE_URL = "https://api.spotify.com/"

        operator fun invoke(): RefreshApi {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(16, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RefreshApi::class.java)

        }

    }

}

interface RefreshApi {

    @POST("api/token")
    fun getRefreshToken(
        @Header("Authorization") authorization : String ,
        @Body refreshTokenBodyModel: RefreshTokenBodyModel
    ): Call<RefreshTokenModel>
}
