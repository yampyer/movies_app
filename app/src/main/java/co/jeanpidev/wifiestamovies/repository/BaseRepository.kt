package co.jeanpidev.wifiestamovies.repository

import android.util.Log
import co.jeanpidev.wifiestamovies.model.ErrorModel
import co.jeanpidev.wifiestamovies.utils.ERROR_TAG
import co.jeanpidev.wifiestamovies.utils.ERROR_UNAUTHORIZED
import co.jeanpidev.wifiestamovies.utils.ERROR_UNKNOWN
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {

    companion object {

        fun <T> handleSuccess(data: T): Result<T> = Result.success(data)

        fun <T> handleError(e: ResponseBody): Result<T> {
            // log error
            Log.e(ERROR_TAG, e.toString())
            val error = parseErrorBody(e)
            return Result.failure(determineError(e, error))
        }

        private fun parseErrorBody(e: ResponseBody): ErrorModel {
            val gson = GsonBuilder().create()
            var error = ErrorModel(ERROR_UNKNOWN)

            if (e !is HttpException) return error

            try {
                val errorBody = e.response()?.errorBody()?.string()
                error = if (errorBody.equals(ERROR_UNAUTHORIZED)) {
                    ErrorModel(ERROR_UNAUTHORIZED)
                } else {
                    gson.fromJson(errorBody, ErrorModel::class.java)
                }
            } catch (e: IOException) {
                Log.e(ERROR_TAG, e.toString())
            } catch (e: JsonSyntaxException) { // json is malformed
                Log.e(ERROR_TAG, e.toString())
            } catch (e: IllegalStateException) { // parsed json resulted in null
                Log.e(ERROR_TAG, e.toString())
            }
            return error
        }

        private fun determineError(originalError: ResponseBody, error: ErrorModel): ResponseBody {
            return when (error.message) {
                ERROR_UNAUTHORIZED -> {
                    originalError
                }
                else -> {
                    originalError
                }
            }
        }
    }
}
