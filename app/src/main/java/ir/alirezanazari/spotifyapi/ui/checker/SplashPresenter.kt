package ir.alirezanazari.spotifyapi.ui.checker

import ir.alirezanazari.spotifyapi.ui.BasePresenter
import ir.alirezanazari.spotifyapi.ui.Presenter


class SplashPresenter : BasePresenter<SplashView>() , Presenter {

    override fun onStart() {

    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        unbindView()
    }

}