package co.jeanpidev.wifiestamovies.repository

import okhttp3.ResponseBody

sealed class Result<T> {
    data class Progress<T>(var loading: Boolean) : Result<T>()
    data class Success<T>(var data: T) : Result<T>()
    data class Failure<T>(val e: ResponseBody) : Result<T>()

    /**
     * This class represents a result which was "spent" and should no longer trigger
     * further behavior.
     *
     * This is useful when we want to represent a one-off type of data or event.
     *
     * To consume data for this result, a programmer will have to call again the original method that created this Result.
     */
    class Done<T> : Result<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): Result<T> = Progress(isLoading)
        fun <T> success(data: T): Result<T> = Success(data)
        fun <T> failure(e: ResponseBody): Result<T> = Failure(e)
        fun <T> done(): Result<T> = Done()
    }

    val extractData: T?
        get() = when(this) {
            is Progress -> null
            is Success -> data
            is Failure -> null
            else -> null
        }
}
