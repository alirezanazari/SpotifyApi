package ir.alirezanazari.spotifyapi

import android.app.Application
import ir.alirezanazari.spotifyapi.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class G : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@G)
            androidLogger()
            modules(appModule)
        }
    }

}