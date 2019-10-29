package ir.alirezanazari.data.provider

import android.content.Context
import android.content.SharedPreferences


class PreferencesProvider(context: Context) {

    companion object{
        const val MUSIC_PREF_NAME = "MUSIC_PREF"
        const val SPOTIFY_TOKEN_PREF = "SPOTIFY_TOKEN"
        const val SPOTIFY_CODE_PREF = "SPOTIFY_CODE"
    }

    private val appContext = context.applicationContext
    val preferences : SharedPreferences
        get() = appContext.getSharedPreferences(MUSIC_PREF_NAME , Context.MODE_PRIVATE)

    fun getToken() : String?{
        return preferences.getString(SPOTIFY_TOKEN_PREF, "")
    }

    fun setToken(token : String){
        preferences.edit().putString(SPOTIFY_TOKEN_PREF , token).apply()
    }

    fun getCode() : String{
    return preferences.getString(SPOTIFY_CODE_PREF, "")
    }

    fun setCode(code : String){
        preferences.edit().putString(SPOTIFY_CODE_PREF , code).apply()
    }

}