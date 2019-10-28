package ir.alirezanazari.spotifyapi.ui.checker


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.alirezanazari.spotifyapi.R
import ir.alirezanazari.spotifyapi.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : BaseFragment() , SplashView {

    private lateinit var presenter: SplashPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this)
        presenter.onCreate()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onLoginNeed() {

    }

    override fun onStartLoader() {
        setLoaderStatus(true)
    }

    override fun onStopLoader() {
        setLoaderStatus(false)
    }

    override fun onShowMessage(message : String) {
        onStopLoader()
        setLoaderStatus(false , message)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun setLoaderStatus(state : Boolean, message : String? = null){
        if (state){
            tv_login.text = getString(R.string.login_hint)
            lyt_loader.visibility = View.VISIBLE
            lyt_login.visibility = View.GONE
        }else{
            if (message != null) tv_login.text = message
            else tv_login.text = getString(R.string.login_hint)
            lyt_loader.visibility = View.GONE
            lyt_login.visibility = View.VISIBLE
        }
    }
}
