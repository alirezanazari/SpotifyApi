package ir.alirezanazari.spotifyapi.ui.search


import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
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
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment(), SearchView {

    private lateinit var mPresenter: SearchPresenter
    private var mArtistsAdapter = GroupAdapter<ViewHolder>()

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
                    AccessTokenProviderImpl(PreferencesProvider(view.context))
                )
            ), Schedulers.io(), AndroidSchedulers.mainThread()
        )
        mPresenter = SearchPresenter(usecase)
        mPresenter.bindView(this)
        setupViewsListener()
    }

    private fun setupViewsListener() {

        et_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                val textForSearch = et_search.text.toString().trim()
                if (textForSearch.length > 2){
                    mPresenter.getSearchArtist(textForSearch)
                }else{
                    Toast.makeText(activity , getString(R.string.short_text_hint) , Toast.LENGTH_SHORT).show()
                }
                return@OnEditorActionListener true
            }
            false
        })

    }

    override fun onSearchResult(artists: List<ArtistEntity>) {

        initArtistsRecycler(artists)

    }

    override fun onStartLoader() {
        setPageState(true)
    }

    override fun onStopLoader() {
        setPageState(false)
    }

    override fun onShowMessage(message: String) {
        setPageState(false)
        mArtistsAdapter.clear()
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()

    }

    private fun initArtistsRecycler(artists: List<ArtistEntity>) {

        val artistItems : List<ArtistSearchItem> = artists.map(::ArtistSearchItem)
        mArtistsAdapter.addAll(artistItems)
        rv_search.apply {
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = mArtistsAdapter
        }

        mArtistsAdapter.setOnItemClickListener{ item , view ->
            (item as? ArtistSearchItem)?.let {
                openArtistPage(it.artist?.id , view)
            }
        }

    }

    private fun openArtistPage(id: String, view: View) {

        val actionOpenArtist = SearchFragmentDirections.actionSearchResult(id)
        Navigation.findNavController(view).navigate(actionOpenArtist)

    }

    private fun setPageState(state : Boolean){
        if (state){
            rv_search.visibility = View.GONE
            lyt_loader.visibility = View.VISIBLE
        }else{
            rv_search.visibility = View.VISIBLE
            lyt_loader.visibility = View.GONE
        }
    }
}
