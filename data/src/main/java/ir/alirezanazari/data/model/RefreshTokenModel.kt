package ir.alirezanazari.data.model

import com.google.gson.annotations.SerializedName


data class RefreshTokenModel(
    @SerializedName("access_token")
    val access_token : String ,
    @SerializedName("token_type")
    val token_type : String ,
    @SerializedName("refresh_token")
    val refresh_token : String
)