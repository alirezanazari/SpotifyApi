package ir.alirezanazari.data.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiConfig {

    companion object {

        const val BASE_URL = "https://api.spotify.com/v1/"

        operator fun invoke(ci: ConnectionIntercepter): Api {

            /* val interceptor = Interceptor { chain ->

                 val url = chain.request()
                     .url()
                     .newBuilder()
                     .addQueryParameter("Authorization" , *//*get from pref maybe*//*)
                  .build()

              val request = chain.request()
                  .newBuilder()
                  .url(url)
                  .build()

              return@Interceptor chain.proceed(request)
          }*/

            val okHttpClient = OkHttpClient.Builder()
                //.addInterceptor(interceptor)
                .addInterceptor(ci)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)

        }

    }


}