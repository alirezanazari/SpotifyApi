package ir.alirezanazari.data.provider

import android.util.Base64
import ir.alirezanazari.data.model.RefreshTokenBodyModel
import ir.alirezanazari.data.net.RefreshTokenApi

class AccessTokenProviderImpl(
    private val preferencesProvider: PreferencesProvider ,
    private val refreshTokenApi: RefreshTokenApi
) : AccessTokenProvider {

    companion object{
        const val REDIRECT_URI = "http://alirezanazari.ir"

    }

    override fun token(): String? {
        return preferencesProvider.getToken()
    }

    override fun refreshToken(): String? {

        val body = RefreshTokenBodyModel(REDIRECT_URI , preferencesProvider.getCode())
        val resp = refreshTokenApi.getRefreshToken(body).execute()
        if (resp.body() != null){
            val token = resp.body()?.token_type + " " + resp.body()?.access_token
            preferencesProvider.setToken(token)
            return token
        }
        return null
    }
}