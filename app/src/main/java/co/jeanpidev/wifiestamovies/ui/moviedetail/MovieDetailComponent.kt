package co.jeanpidev.wifiestamovies.ui.moviedetail

import co.jeanpidev.wifiestamovies.di.ActivityScoped
import dagger.Subcomponent

@ActivityScoped
@Subcomponent(modules = [MovieDetailModule::class])
interface MovieDetailComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieDetailComponent
    }

    fun inject(activity: MovieDetailActivity)
}
