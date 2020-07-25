package co.jeanpidev.wifiestamovies.di

import co.jeanpidev.wifiestamovies.ui.listmovie.ListMovieComponent
import co.jeanpidev.wifiestamovies.ui.moviedetail.MovieDetailComponent
import co.jeanpidev.wifiestamovies.ui.splash.SplashComponent
import dagger.Module

@Module(subcomponents = [ListMovieComponent::class, SplashComponent::class, MovieDetailComponent::class])
class AppSubComponents
