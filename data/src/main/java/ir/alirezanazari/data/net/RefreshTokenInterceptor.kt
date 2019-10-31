package ir.alirezanazari.data.net

import android.util.Base64
import ir.alirezanazari.data.provider.AccessTokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class RefreshTokenInterceptor : Interceptor {

    companion object {
        const val CLIENT_ID = "90d847e3b4c84d81b0bfb85ed24b1984"
        const val CLIENT_SECRET = "946bd7539124487f803b2901efaef335"

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val credential = "$CLIENT_ID:$CLIENT_SECRET"
        val basic = "Basic " + Base64.encodeToString(credential.toByteArray(), Base64.NO_WRAP)

        val original = chain.request()
        val request = original.newBuilder()
            .header("Authorization", basic)
            .header("Content-Type", "application/json")
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)

    }
}