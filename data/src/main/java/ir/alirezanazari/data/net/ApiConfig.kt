package ir.alirezanazari.data.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import ir.alirezanazari.data.provider.PreferencesProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiConfig {

    companion object {

        const val BASE_URL = "https://api.spotify.com/v1/"

        operator fun invoke(pref : PreferencesProvider): Api {

             val interceptor = Interceptor { chain ->

                 //todo :// save token in pref not safe encrypt text or find another way
                 val url = chain.request()
                     .url()
                     .newBuilder()
                     .addQueryParameter("Authorization" , pref.getToken())
                  .build()

              val request = chain.request()
                  .newBuilder()
                  .url(url)
                  .build()

              return@Interceptor chain.proceed(request)
          }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(16 , TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                //.addCallAdapterFactory(CoroutineCallAdapterFactory()) //for coroutines
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)

        }

    }


}