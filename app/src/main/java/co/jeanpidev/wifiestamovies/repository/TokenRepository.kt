package co.jeanpidev.wifiestamovies.repository

import co.jeanpidev.wifiestamovies.network.service.AuthApi
import co.jeanpidev.wifiestamovies.network.service.MoviesApi
import co.jeanpidev.wifiestamovies.utils.helpers.AccountHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRepository @Inject constructor(private val accountHelper: AccountHelper,
                                          private val authApi: AuthApi): BaseRepository() {

    suspend fun getToken(email: String): Result<Boolean> {
        var result: Result<Boolean> = handleSuccess(false)
        val response = authApi.getToken(email)
        response.body()?.let { tokenResponse ->
            val success = tokenResponse.key.isNotEmpty() && tokenResponse.email.isNotEmpty()
            if (success) accountHelper.saveToken(tokenResponse)
            result = handleSuccess(success)
        }
        response.errorBody()?.let {
            result = handleError(it)
        }

        return result
    }
}
