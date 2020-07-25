package co.jeanpidev.wifiestamovies.repository

import androidx.lifecycle.MutableLiveData
import co.jeanpidev.wifiestamovies.model.Movie
import co.jeanpidev.wifiestamovies.network.service.MoviesApi
import co.jeanpidev.wifiestamovies.utils.PARAM_PAGE
import co.jeanpidev.wifiestamovies.utils.PARAM_QUERY
import co.jeanpidev.wifiestamovies.utils.PARAM_SORT
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val moviesApi: MoviesApi): BaseRepository() {

    suspend fun getMovies(params: HashMap<String, String>): MutableLiveData<Result<List<Movie>>> {
        val result = MutableLiveData<Result<List<Movie>>>()
        result.value = Result.loading(true)
        val response = moviesApi.getAllMovies(params[PARAM_PAGE]?.toInt(), params[PARAM_SORT], params[PARAM_QUERY])
        response.body()?.let { movies ->
            result.value = handleSuccess(movies.filter { !it.adult })
        }
        response.errorBody()?.let {
            result.value = handleError(it)
        }

        return result
    }

    suspend fun getMovie(movieId: Int): MutableLiveData<Result<Movie>> {
        val result = MutableLiveData<Result<Movie>>()
        result.value = Result.loading(true)
        val response = moviesApi.getMovieById(movieId)
        response.body()?.let { movie ->
            result.value = handleSuccess(movie)
        }
        response.errorBody()?.let {
            result.value = handleError(it)
        }

        return result
    }
}
