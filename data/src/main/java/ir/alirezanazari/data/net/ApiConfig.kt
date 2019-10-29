package ir.alirezanazari.data.net

import ir.alirezanazari.data.provider.AccessTokenProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiConfig {

    companion object {

        private const val BASE_URL = "https://api.spotify.com/v1/"

        operator fun invoke(
            accessTokenProvider: AccessTokenProvider
        ): Api {

            val okHttpClient = OkHttpClient.Builder()
                .authenticator(AccessTokenAuthenticator(accessTokenProvider))
                .addInterceptor(AccessTokenInterceptor(accessTokenProvider))
                .connectTimeout(16, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)

        }

    }


}