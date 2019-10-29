package ir.alirezanazari.data.net

import android.content.Context
import android.net.ConnectivityManager
import ir.alirezanazari.domain.exception.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response


class ConnectionIntercepter(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw NoConnectivityException()
        else
            return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = context.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}