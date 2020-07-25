package co.jeanpidev.wifiestamovies.ui.splash

import androidx.lifecycle.ViewModel
import co.jeanpidev.wifiestamovies.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class SplashModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class) abstract fun bindSplashViewModel(
        viewModel: SplashViewModel): ViewModel
}
