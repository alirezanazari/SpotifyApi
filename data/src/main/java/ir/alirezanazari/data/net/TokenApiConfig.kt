package ir.alirezanazari.data.net


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class TokenApiConfig {

    companion object {

        private const val BASE_URL = "https://api.spotify.com/"

        operator fun invoke(interceptor: TokenInterceptor): TokenApi {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(16, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TokenApi::class.java)

        }

    }

}
