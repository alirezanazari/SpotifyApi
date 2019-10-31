package ir.alirezanazari.data.net

import ir.alirezanazari.data.model.RefreshTokenBodyModel
import ir.alirezanazari.data.model.RefreshTokenModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface TokenApi {

    @POST("api/token")
    fun getRefreshToken(
        @Body refreshTokenBodyModel: RefreshTokenBodyModel
    ): Call<RefreshTokenModel>
}
