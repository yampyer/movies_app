package co.jeanpidev.wifiestamovies.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    val adult: Boolean,
    val description: String,
    val image: String,
    @SerializedName("tagline")
    var tagLine: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    var status: String? = null,
    var reviews: List<Review>? = null,
    var cast: List<Character>? = null
)
