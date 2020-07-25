package co.jeanpidev.wifiestamovies.di

import co.jeanpidev.wifiestamovies.base.BaseActivity
import co.jeanpidev.wifiestamovies.ui.listmovie.ListMovieComponent
import co.jeanpidev.wifiestamovies.network.NetworkModule
import co.jeanpidev.wifiestamovies.ui.moviedetail.MovieDetailComponent
import co.jeanpidev.wifiestamovies.ui.splash.SplashComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Main component of the app, created and persisted in the Application class. All modules need to be added here.
 */
@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, NetworkModule::class, AppSubComponents::class])
    interface AppComponent {

    fun splashComponent(): SplashComponent.Factory
    fun listMovieComponent(): ListMovieComponent.Factory
    fun movieDetailComponent(): MovieDetailComponent.Factory
    fun inject(activity: BaseActivity)
}
