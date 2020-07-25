package co.jeanpidev.wifiestamovies.application

import android.app.Application
import co.jeanpidev.wifiestamovies.di.AppComponent
import co.jeanpidev.wifiestamovies.di.AppModule
import co.jeanpidev.wifiestamovies.di.DaggerAppComponent

class App : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}
