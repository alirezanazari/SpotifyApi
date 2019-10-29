package ir.alirezanazari.data.net

import ir.alirezanazari.data.provider.AccessTokenProvider
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AccessTokenAuthenticator(
    private val tokenProvider: AccessTokenProvider
) : Authenticator {

    private val headerKey = "Authorization"

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = tokenProvider.token() ?: return null

        synchronized(this) {
            val newToken = tokenProvider.token()

            if (response.request().header(headerKey) != null) {

                if (newToken != token) {
                    return response.request()
                        .newBuilder()
                        .removeHeader(headerKey)
                        .addHeader(headerKey,  newToken)
                        .build()
                }

                val updatedToken = tokenProvider.refreshToken() ?: return null

                return response.request()
                    .newBuilder()
                    .removeHeader(headerKey)
                    .addHeader(headerKey, updatedToken)
                    .build()
            }
        }
        return null
    }
}