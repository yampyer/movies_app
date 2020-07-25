package co.jeanpidev.wifiestamovies.network.interceptor

import co.jeanpidev.wifiestamovies.utils.API_KEY_HEADER
import co.jeanpidev.wifiestamovies.utils.helpers.AccountHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val accountHelper: AccountHelper) : Interceptor {
    @Throws(IOException::class) override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder().addHeader("Accept", "*/*")
            .addHeader(API_KEY_HEADER, accountHelper.token).build()
        return chain.proceed(newRequest)
    }
}
