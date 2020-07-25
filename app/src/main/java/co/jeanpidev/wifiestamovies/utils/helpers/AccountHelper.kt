package co.jeanpidev.wifiestamovies.utils.helpers

import co.jeanpidev.wifiestamovies.model.TokenResponse
import co.jeanpidev.wifiestamovies.utils.KEY_EMAIL
import co.jeanpidev.wifiestamovies.utils.KEY_TOKEN
import javax.inject.Inject

class AccountHelper @Inject constructor(private val preferencesHelper: PreferencesHelper) {

    val isTokenAvailable: Boolean
        get() {
            var returned = false
            val token = preferencesHelper.get(KEY_TOKEN)
            if (token.isNotEmpty()) {
                returned = true
            }
            return returned
        }

    val token: String
        get() {
            return preferencesHelper.get(KEY_TOKEN)
        }

    val email: String
        get() {
            return preferencesHelper.get(KEY_EMAIL)
        }

    fun clearData() {
        preferencesHelper.clearSharedPrefs() // clear user data
    }

    fun saveToken(tokenResponse: TokenResponse) {
        preferencesHelper.save(KEY_TOKEN, tokenResponse.key)
        preferencesHelper.save(KEY_EMAIL, tokenResponse.email)
    }
}
