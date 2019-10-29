package ir.alirezanazari.spotifyapi.ui.search

import ir.alirezanazari.domain.entity.ArtistEntity
import ir.alirezanazari.spotifyapi.ui.BaseView


interface SearchView: BaseView {

    fun onSearchResult(artists: List<ArtistEntity>)

}