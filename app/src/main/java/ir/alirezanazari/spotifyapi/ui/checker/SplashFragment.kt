package ir.alirezanazari.spotifyapi.ui.checker


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import ir.alirezanazari.spotifyapi.R
import ir.alirezanazari.spotifyapi.internal.Const
import ir.alirezanazari.spotifyapi.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : BaseFragment() , SplashView {

    companion object{
        const val REQUEST_CODE_SPOTIFY_LOGIN = 123
    }

    private lateinit var mPresenter: SplashPresenter
    private lateinit var mView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo:// use di
        mView = view
        mPresenter = SplashPresenter()
        mPresenter.bindView(this)
        mPresenter.onCreate()
        setupViewsListener()
    }

    private fun setupViewsListener() {

        btn_login.setOnClickListener {
            onStartLoader()
            onRequestCheckLoginState()
        }
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
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

    override fun onRequestCheckLoginState() {

        if (activity == null) return

        val builder : AuthenticationRequest.Builder =
            AuthenticationRequest.Builder(Const.Net.CLIENT_ID , AuthenticationResponse.Type.TOKEN , Const.Net.REDIRECT_URI)
        builder.setScopes(arrayOf("user-library-read"))

        val request = builder.build()
        AuthenticationClient.openLoginActivity(activity , REQUEST_CODE_SPOTIFY_LOGIN , request)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SPOTIFY_LOGIN){
            val response =  AuthenticationClient.getResponse(resultCode, data)

            when(response.type){

                AuthenticationResponse.Type.TOKEN -> {
                    getApplication().spotifyToken = response.accessToken
                    openSearchPage()
                }

                AuthenticationResponse.Type.ERROR -> {
                    onShowMessage(getString(R.string.spotify_connection_fail))
                }

                else -> {
                    onStopLoader()
                }
            }

        }
    }

    private fun openSearchPage() {

        val actionOpenSearch = SplashFragmentDirections.actionInter()
        Navigation.findNavController(mView).navigate(actionOpenSearch)

    }

    override fun onDestroy() {
        mPresenter.onDestroy()
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
