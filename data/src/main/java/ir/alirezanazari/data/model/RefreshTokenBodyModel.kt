package ir.alirezanazari.data.model


data class RefreshTokenBodyModel (
    val redirect_uri : String,
    val code : String,
    val grant_type : String = "authorization_code"
)