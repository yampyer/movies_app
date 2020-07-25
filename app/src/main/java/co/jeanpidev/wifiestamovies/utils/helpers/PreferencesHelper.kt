package co.jeanpidev.wifiestamovies.utils.helpers

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import javax.inject.Inject

/**
 * Shared Preferences helper.
 */
class PreferencesHelper @Inject constructor(context: Context) {

    private var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
        context)

    fun save(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun save(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun get(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, default)
    }

    /**
     * Clears everything in the SharedPreferences.
     */
    fun clearSharedPrefs() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
