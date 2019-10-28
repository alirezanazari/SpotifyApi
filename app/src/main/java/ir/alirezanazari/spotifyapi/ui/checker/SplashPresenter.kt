package ir.alirezanazari.spotifyapi.ui.checker

import ir.alirezanazari.spotifyapi.ui.BasePresenter
import ir.alirezanazari.spotifyapi.ui.Presenter


class SplashPresenter : BasePresenter<SplashView>() , Presenter {

    override fun onStart() {
        getView()?.onRequestCheckLoginState()
    }

    override fun onCreate() {
        getView()?.onStartLoader()
    }

    override fun onDestroy() {
        unbindView()
    }

}