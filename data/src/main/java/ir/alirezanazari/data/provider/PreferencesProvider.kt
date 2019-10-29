package ir.alirezanazari.data.provider

import android.content.Context
import android.content.SharedPreferences


class PreferencesProvider(context: Context) {

    companion object{
        const val MUSIC_PREF_NAME = "MUSIC_PREF"
        const val SPOTIFY_TOKEN_PREF = "SPOTIFY_TOKEN"
        const val TOKEN_PERFIX = "Bearer "
    }

    private val appContext = context.applicationContext
    val preferences : SharedPreferences
        get() = appContext.getSharedPreferences(MUSIC_PREF_NAME , Context.MODE_PRIVATE)

    fun getToken() : String{
        val token = preferences.getString(SPOTIFY_TOKEN_PREF , "")
        return TOKEN_PERFIX + token
    }

    fun setToken(token : String){
        preferences.edit().putString(SPOTIFY_TOKEN_PREF , token).apply()
    }

}