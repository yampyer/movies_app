package co.jeanpidev.wifiestamovies.ui.listmovie

import androidx.lifecycle.ViewModel
import co.jeanpidev.wifiestamovies.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ListMovieModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListMovieViewModel::class) abstract fun bindListMovieViewModel(
        viewModel: ListMovieViewModel): ViewModel
}
