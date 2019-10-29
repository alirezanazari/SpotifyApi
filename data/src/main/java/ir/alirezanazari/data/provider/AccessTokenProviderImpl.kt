package ir.alirezanazari.data.provider

import android.util.Base64
import ir.alirezanazari.data.model.RefreshTokenBodyModel
import ir.alirezanazari.data.net.RefreshApi
import ir.alirezanazari.data.net.RefreshTokenApiConfig

class AccessTokenProviderImpl(
    private val preferencesProvider: PreferencesProvider
) : AccessTokenProvider {

    companion object{
        const val REDIRECT_URI = "http://alirezanazari.ir"
        const val CLIENT_ID = "90d847e3b4c84d81b0bfb85ed24b1984"
        const val CLIENT_SECRET = "946bd7539124487f803b2901efaef335"

    }

    private var refreshTokenApi: RefreshApi

    init {
        val refreshToken = RefreshTokenApiConfig
        refreshTokenApi = refreshToken.invoke()
    }

    override fun token(): String? {
        return preferencesProvider.getToken()
    }

    override fun refreshToken(): String? {

        val credential = "$CLIENT_ID:$CLIENT_SECRET"
        val basic = "Basic " + Base64.encodeToString(credential.toByteArray(), Base64.NO_WRAP)

        val body = RefreshTokenBodyModel(REDIRECT_URI , preferencesProvider.getCode())
        val resp = refreshTokenApi.getRefreshToken(basic , body).execute()
        if (resp.body() != null){
            val token = resp.body()?.token_type + " " + resp.body()?.access_token
            preferencesProvider.setToken(token)
            return token
        }
        return null
    }
}