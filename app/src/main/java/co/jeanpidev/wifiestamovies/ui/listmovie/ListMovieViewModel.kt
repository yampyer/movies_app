package co.jeanpidev.wifiestamovies.ui.listmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.jeanpidev.wifiestamovies.base.BaseViewModel
import co.jeanpidev.wifiestamovies.model.Movie
import co.jeanpidev.wifiestamovies.repository.MovieRepository
import co.jeanpidev.wifiestamovies.repository.Result
import co.jeanpidev.wifiestamovies.utils.PARAM_PAGE
import co.jeanpidev.wifiestamovies.utils.PARAM_QUERY
import co.jeanpidev.wifiestamovies.utils.PARAM_SORT
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListMovieViewModel @Inject constructor(private val movieRepository: MovieRepository): BaseViewModel() {

    private val movieList = MutableLiveData<Result<List<Movie>>>()

    private val sortVisibility = MutableLiveData<Boolean>()

    private val sortValue = MutableLiveData<String>()

    private val queryValue = MutableLiveData<String>()

    private var page = 1

    init {
        sortVisibility.value = false
    }

    fun getPage() = page

    fun getMovieList() = movieList

    fun getSortVisibility() = sortVisibility

    fun getSortValue() = sortValue

    fun getQueryValue() = queryValue

    fun getAllMovies(actualPage: Int = 1) {
        movieList.postValue(Result.loading(true))
        val params = HashMap<String, String>()
        params[PARAM_PAGE] = actualPage.toString()
        sortValue.value?.let {
            params[PARAM_SORT] = it
        }
        queryValue.value?.let {
            if (it.isNotEmpty())
                params[PARAM_QUERY] = it
        }
        viewModelScope.launch {
            val movies = movieRepository.getMovies(params)
            page = actualPage
            movieList.postValue(movies.value)
        }
    }

    fun getNextPage() {
        page++
        getAllMovies(page)
    }
}
