package ir.alirezanazari.data.net

import io.reactivex.Observable
import ir.alirezanazari.data.provider.AccessTokenProvider
import ir.alirezanazari.data.provider.PreferencesProvider
import ir.alirezanazari.domain.entity.AlbumEntity
import ir.alirezanazari.domain.entity.ArtistEntity


class NetworkDataManagerImpl(
    accessTokenProvider: AccessTokenProvider
) : NetworkDataManager{

    private var mApi : Api

    init {
        val apiConfig = ApiConfig
        mApi = apiConfig.invoke(accessTokenProvider)
    }

    override fun getSearchArtistResult(query: String): Observable<List<ArtistEntity>> {
        return mApi.getSearchArtist(query).map {
            it.artists.items.map { artist ->
                ArtistEntity(artist.id , artist.name , artist.images[0]?.url , artist.uri)
            }
        }
    }

    override fun getArtistAlbumsById(id: String): Observable<List<AlbumEntity>> {
        return mApi.getArtistAlbum(id).map {
            it.items.map { album ->
                AlbumEntity(album.id , album.name , album.artists[0]?.name , album.images[0]?.url , album.uri )
            }
        }
    }
}