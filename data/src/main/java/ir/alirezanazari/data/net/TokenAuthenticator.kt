package ir.alirezanazari.data.net

import ir.alirezanazari.data.provider.AccessTokenProvider
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val tokenProvider: AccessTokenProvider
) : Authenticator {

    private val headerKey = "Authorization"

    override fun authenticate(route: Route?, response: Response): Request? {

        synchronized(this) {

            val updatedToken = tokenProvider.refreshToken() ?: return null

            return response.request()
                .newBuilder()
                .removeHeader(headerKey)
                .addHeader(headerKey, updatedToken)
                .build()
        }
    }
}