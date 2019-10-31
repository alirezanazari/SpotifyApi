package ir.alirezanazari.data.net

import android.util.Log
import ir.alirezanazari.data.provider.AccessTokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val tokenProvider: AccessTokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider.token()
        val authenticatedRequest = chain.request()
            .newBuilder()
            .addHeader("Authorization", token)
            .build()
        val response = chain.proceed(authenticatedRequest)
        if (response.code() == 400 || response.code() == 401) {
            return chain.proceed(chain.request())
        }
        return response
    }
}