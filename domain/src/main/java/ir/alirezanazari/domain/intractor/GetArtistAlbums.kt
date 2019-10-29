package ir.alirezanazari.domain.intractor

import io.reactivex.Observable
import io.reactivex.Scheduler
import ir.alirezanazari.domain.entity.AlbumEntity
import ir.alirezanazari.domain.repository.MusicRepository


class GetArtistAlbums (
    private val repository: MusicRepository,
    io : Scheduler,
    ui : Scheduler
): UseCase<List<AlbumEntity> , String>(io , ui)  {

    override fun build(param : String): Observable<List<AlbumEntity>> {
        return repository.getArtistAlbums(param)
    }

}