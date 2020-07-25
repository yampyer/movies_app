package co.jeanpidev.wifiestamovies.network.service

import co.jeanpidev.wifiestamovies.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movies")
    suspend fun getAllMovies(@Query("page") page: Int?, @Query("sort") sort: String?, @Query("q") query: String?): Response<List<Movie>>

    @GET("movies/{id}")
    suspend fun getMovieById(@Path("id") movieId: Int): Response<Movie>
}
