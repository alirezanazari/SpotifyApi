package ir.alirezanazari.domain.repository

import io.reactivex.Observable
import ir.alirezanazari.domain.entity.AlbumEntity
import ir.alirezanazari.domain.entity.ArtistEntity


interface MusicRepository {

    fun getSearchArtists(query : String) : Observable<List<ArtistEntity>>
    fun getArtistAlbums(artistId : String) : Observable<List<AlbumEntity>>

}