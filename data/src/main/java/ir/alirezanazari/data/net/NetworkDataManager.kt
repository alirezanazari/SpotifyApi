package ir.alirezanazari.data.net

import io.reactivex.Observable
import ir.alirezanazari.domain.entity.AlbumEntity
import ir.alirezanazari.domain.entity.ArtistEntity


interface NetworkDataManager {

    fun getSearchArtistResult(query: String): Observable<List<ArtistEntity>>
    fun getArtistAlbumsById(id : String): Observable<List<AlbumEntity>>

}