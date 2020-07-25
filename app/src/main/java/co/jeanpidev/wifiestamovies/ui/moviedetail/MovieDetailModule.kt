package co.jeanpidev.wifiestamovies.ui.moviedetail

import androidx.lifecycle.ViewModel
import co.jeanpidev.wifiestamovies.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class MovieDetailModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class) abstract fun bindMovieDetailViewModel(
        viewModel: MovieDetailViewModel): ViewModel
}
