package co.jeanpidev.wifiestamovies.ui.splash

import co.jeanpidev.wifiestamovies.di.ActivityScoped
import dagger.Subcomponent

@ActivityScoped
@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashComponent
    }

    fun inject(activity: SplashActivity)
}
