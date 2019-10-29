package ir.alirezanazari.spotifyapi.ui.search

import io.reactivex.observers.DisposableObserver
import ir.alirezanazari.domain.entity.ArtistEntity
import ir.alirezanazari.domain.intractor.GetSearchArtists
import ir.alirezanazari.spotifyapi.ui.BasePresenter
import ir.alirezanazari.spotifyapi.ui.Presenter


class SearchPresenter(
    private val useCase : GetSearchArtists
) : BasePresenter<SearchView>() , Presenter {

    override fun onStart() {
        //nothing yet
    }

    override fun onCreate() {
        getView()?.setEmptyAdapterToList()
    }

    override fun onDestroy() {
        useCase.clearDisposable()
        unbindView()
    }

    fun getSearchArtist(text : String){
        useCase.execute(object : DisposableObserver<List<ArtistEntity>>(){
            override fun onComplete() {
                getView()?.onStopLoader()
            }

            override fun onNext(result : List<ArtistEntity>) {
                getView()?.onSearchResult(result)
                getView()?.onStopLoader()
            }

            override fun onError(e: Throwable) {
                getView()?.onShowMessage("Error to get result from server")
            }
        } , text)
    }
}