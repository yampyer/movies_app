package co.jeanpidev.wifiestamovies.network.service

import co.jeanpidev.wifiestamovies.model.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthApi {

    @GET("key")
    suspend fun getToken(@Query("email") email: String): Response<TokenResponse>
}
