package ir.alirezanazari.spotifyapi.ui

import androidx.fragment.app.Fragment
import ir.alirezanazari.spotifyapi.G


open class BaseFragment : Fragment() {

    fun getApplication() : G{
        return  activity?.application as G
    }
}