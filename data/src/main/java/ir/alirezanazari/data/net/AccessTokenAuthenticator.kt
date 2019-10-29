package ir.alirezanazari.data.net

import ir.alirezanazari.data.provider.AccessTokenProvider
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AccessTokenAuthenticator(
    private val tokenProvider: AccessTokenProvider
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = tokenProvider.token() ?: return null

        synchronized(this) {
            val newToken = tokenProvider.token()

            if (response.request().header("Authorization") != null) {

                if (newToken != token) {
                    return response.request()
                        .newBuilder()
                        .removeHeader("Authorization")
                        .addHeader("Authorization",  newToken)
                        .build()
                }

                val updatedToken = tokenProvider.refreshToken() ?: return null

                return response.request()
                    .newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", updatedToken)
                    .build()
            }
        }
        return null
    }
}