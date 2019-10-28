package ir.alirezanazari.spotifyapi.ui


abstract class BasePresenter<V>{

    private var view: V? = null //can be weakReference

    fun bindView(view: V) {
        this.view = view
    }

    fun unbindView() {
        this.view = null
    }

    fun getView(): V? {
        return view
    }

}