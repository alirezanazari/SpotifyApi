package ir.alirezanazari.data.repository

import io.reactivex.Observable
import ir.alirezanazari.data.net.NetworkDataManager
import ir.alirezanazari.domain.entity.AlbumEntity
import ir.alirezanazari.domain.entity.ArtistEntity
import ir.alirezanazari.domain.repository.MusicRepository


class MusicRepositoryImpl(
    private val net : NetworkDataManager
) :MusicRepository {

    override fun getSearchArtists(query: String): Observable<List<ArtistEntity>> {
        return net.getSearchArtistResult(query)
    }

    override fun getArtistAlbums(artistId: String): Observable<List<AlbumEntity>> {
        return net.getArtistAlbumsById(artistId)
    }
}