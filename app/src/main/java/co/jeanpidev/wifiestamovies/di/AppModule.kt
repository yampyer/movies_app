package co.jeanpidev.wifiestamovies.di

import android.content.Context
import co.jeanpidev.wifiestamovies.application.App
import co.jeanpidev.wifiestamovies.utils.helpers.AccountHelper
import co.jeanpidev.wifiestamovies.utils.helpers.PreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Defines all the classes that need to be provided in the scope of the app (e.g Helpers).
 */
@Module
class AppModule(private val application: App) {

    @Provides @Singleton fun provideApp() = application

    @Singleton @Provides fun provideContext(): Context = application.applicationContext

    @Singleton @Provides fun providePreferencesHelper(context: Context): PreferencesHelper =
        PreferencesHelper(context)

    @Singleton @Provides fun provideAccountHelper(preferencesHelper: PreferencesHelper): AccountHelper =
        AccountHelper(preferencesHelper)
}
