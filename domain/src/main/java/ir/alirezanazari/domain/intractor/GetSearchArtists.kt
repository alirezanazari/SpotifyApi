package ir.alirezanazari.domain.intractor

import io.reactivex.Observable
import io.reactivex.Scheduler
import ir.alirezanazari.domain.entity.ArtistEntity
import ir.alirezanazari.domain.repository.MusicRepository


class GetSearchArtists(
    private val repository: MusicRepository ,
    io : Scheduler ,
    ui : Scheduler
): UseCase<List<ArtistEntity> , String>(io , ui) {

    override fun build(param: String): Observable<List<ArtistEntity>> {
        return repository.getSearchArtists(param)
    }

}