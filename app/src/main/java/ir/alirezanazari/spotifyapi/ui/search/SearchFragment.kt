package ir.alirezanazari.spotifyapi.ui.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.alirezanazari.data.net.NetworkDataManagerImpl
import ir.alirezanazari.data.provider.AccessTokenProviderImpl
import ir.alirezanazari.data.provider.PreferencesProvider
import ir.alirezanazari.data.repository.MusicRepositoryImpl
import ir.alirezanazari.domain.entity.ArtistEntity
import ir.alirezanazari.domain.intractor.GetSearchArtists
import ir.alirezanazari.spotifyapi.R
import ir.alirezanazari.spotifyapi.ui.BaseFragment


class SearchFragment : BaseFragment(), SearchView {

    private lateinit var mPresenter: SearchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usecase = GetSearchArtists(
            MusicRepositoryImpl(
                NetworkDataManagerImpl(
                    PreferencesProvider(view.context),
                    AccessTokenProviderImpl(PreferencesProvider(view.context))
                )
            ), Schedulers.io(), AndroidSchedulers.mainThread()
        )
        mPresenter = SearchPresenter(usecase)
        mPresenter.bindView(this)
        mPresenter.getSearchArtist("Bob")
    }

    override fun onSearchResult(artists: List<ArtistEntity>) {
        Toast.makeText(activity, artists[0]?.name, Toast.LENGTH_LONG).show()
    }

    override fun setEmptyAdapterToList() {

    }

    override fun onStartLoader() {

    }

    override fun onStopLoader() {

    }

    override fun onShowMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()

    }
}
