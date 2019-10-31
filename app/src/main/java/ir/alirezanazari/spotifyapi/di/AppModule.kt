package ir.alirezanazari.spotifyapi.di

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.alirezanazari.data.net.*
import ir.alirezanazari.data.provider.AccessTokenProvider
import ir.alirezanazari.data.provider.AccessTokenProviderImpl
import ir.alirezanazari.data.provider.PreferencesProvider
import ir.alirezanazari.data.repository.MusicRepositoryImpl
import ir.alirezanazari.domain.intractor.GetSearchArtists
import ir.alirezanazari.domain.repository.MusicRepository
import ir.alirezanazari.spotifyapi.ui.checker.SplashPresenter
import ir.alirezanazari.spotifyapi.ui.search.SearchPresenter
import org.koin.dsl.module


val appModule = module {

    single { PreferencesProvider(get()) }
    factory { SplashPresenter() }

    single { TokenInterceptor() }
    single { TokenApiConfig(get()) }
    single<AccessTokenProvider> { AccessTokenProviderImpl(get() , get()) }
    single { AccessTokenInterceptor(get()) }
    single { AccessTokenAuthenticator(get()) }
    single { ApiConfig(get() , get()) }
    single<NetworkDataManager> { NetworkDataManagerImpl(get()) }
    single<MusicRepository> { MusicRepositoryImpl(get()) }
    factory { GetSearchArtists(get() , Schedulers.io() , AndroidSchedulers.mainThread()) }
    factory { SearchPresenter(get()) }
}