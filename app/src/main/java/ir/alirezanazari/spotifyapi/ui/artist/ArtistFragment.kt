package ir.alirezanazari.spotifyapi.ui.artist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.alirezanazari.spotifyapi.R
import ir.alirezanazari.spotifyapi.ui.BaseFragment


class ArtistFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artist, container, false)
    }


}
