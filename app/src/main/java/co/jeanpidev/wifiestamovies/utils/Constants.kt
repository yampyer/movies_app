package co.jeanpidev.wifiestamovies.utils

import co.jeanpidev.wifiestamovies.BuildConfig

// region preferences
const val KEY_TOKEN: String = "KeyToken"
const val KEY_EMAIL: String = "KeyEmail"

// endregion

// region api errors
const val ERROR_UNKNOWN = "Server unknown error"
const val ERROR_UNAUTHORIZED = "Unauthorized"
// endregion

// region tags
const val ERROR_TAG = "Error"
// endregion

// region urls
const val IMAGES_URL = "${BuildConfig.BaseURL}images"
const val SIZE_PARAM = "?size=w185"
const val API_KEY_HEADER = "api-key"
const val PARAM_SORT = "sort"
const val PARAM_PAGE = "page"
const val PARAM_QUERY = "q"
// endregion

// region intent extra keys
const val EXTRA_MOVIE_ID = "extra_movie_id"
const val EXTRA_MOVIE_NAME = "extra_movie_name"
// endregion

// region view types
const val VIEW_TYPE_ITEM = 0
const val VIEW_TYPE_LOADING = 1
// endregion

// region sort values
const val SORT_TITLE_ASC = "title.asc"
const val SORT_TITLE_DESC = "title.desc"
const val SORT_DATE_ASC = "date.asc"
const val SORT_DATE_DESC = "date.desc"
const val SORT_POPULARITY_ASC = "popularity.asc"
const val SORT_POPULARITY_DESC = "popularity.desc"
// endregion