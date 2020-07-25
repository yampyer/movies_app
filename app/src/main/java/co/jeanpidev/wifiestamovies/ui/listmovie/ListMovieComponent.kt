package co.jeanpidev.wifiestamovies.ui.listmovie

import co.jeanpidev.wifiestamovies.di.ActivityScoped
import dagger.Subcomponent

@ActivityScoped
@Subcomponent(modules = [ListMovieModule::class])
interface ListMovieComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ListMovieComponent
    }

    fun inject(activity: ListMovieActivity)
}
