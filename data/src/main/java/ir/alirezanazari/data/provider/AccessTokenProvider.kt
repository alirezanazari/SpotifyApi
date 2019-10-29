package ir.alirezanazari.data.provider


interface AccessTokenProvider {

    fun token(): String
    fun refreshToken(): String?
}