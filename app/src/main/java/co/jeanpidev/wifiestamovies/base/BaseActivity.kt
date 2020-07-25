package co.jeanpidev.wifiestamovies.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import co.jeanpidev.wifiestamovies.ui.components.ProgressDialogFragment
import co.jeanpidev.wifiestamovies.utils.extension.app

abstract class BaseActivity : AppCompatActivity() {

    private var progressDialogFragment: ProgressDialogFragment? = null
    private var isShowingProgress: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        app.component.inject(this)
        super.onCreate(savedInstanceState)
        if (progressDialogFragment == null) {
            progressDialogFragment = ProgressDialogFragment.newInstance(this)
        }
    }

    fun toggleProgress(showProgress: Boolean) {
        if (showProgress && !isShowingProgress) {
            isShowingProgress = true
            progressDialogFragment?.show()
        } else if (!showProgress && isShowingProgress) {
            isShowingProgress = false
            progressDialogFragment?.dismiss()
        }
    }

    fun showError(@StringRes errorMessage: Int) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        progressDialogFragment?.dismiss()
        progressDialogFragment = null
        super.onDestroy()
    }
}
