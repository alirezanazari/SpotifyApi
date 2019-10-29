package ir.alirezanazari.data.net

import io.reactivex.Observable
import ir.alirezanazari.data.model.AlbumModel
import ir.alirezanazari.data.model.ArtistModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


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