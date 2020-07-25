package co.jeanpidev.wifiestamovies.ui.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.jeanpidev.wifiestamovies.base.BaseViewModel
import co.jeanpidev.wifiestamovies.model.Movie
import co.jeanpidev.wifiestamovies.repository.MovieRepository
import co.jeanpidev.wifiestamovies.repository.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository): BaseViewModel() {

    private val movie = MutableLiveData<Result<Movie>>()

    fun getMovie() = movie

    fun getMovie(movieId: Int) {
        movie.postValue(Result.loading(true))
        viewModelScope.launch {
            val movieById = movieRepository.getMovie(movieId)
            movie.postValue(movieById.value)
        }
    }
}
