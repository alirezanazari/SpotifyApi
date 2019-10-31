package ir.alirezanazari.data.net

import io.reactivex.Observable
import ir.alirezanazari.data.model.AlbumModel
import ir.alirezanazari.data.model.ArtistModel
import ir.alirezanazari.data.model.RefreshTokenBodyModel
import ir.alirezanazari.data.model.RefreshTokenModel
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @GET("search")
    fun getSearchArtist(
        @Query("q") query : String ,
        @Query("type") type : String = "artist"
    ) : Observable<ArtistModel>

    @GET("artists/{id}/albums")
    fun getArtistAlbum(
        @Path("id") id : String,
        @Query("include_groups") group : String = "album"
    ) : Observable<AlbumModel>

}


interface TokenApi {

    @POST("api/token")
    fun getRefreshToken(
        @Body refreshTokenBodyModel: RefreshTokenBodyModel
    ): Call<RefreshTokenModel>
}
