package co.jeanpidev.wifiestamovies.ui.components

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.content.Context
import co.jeanpidev.wifiestamovies.databinding.LayoutProgressDialogBinding

class ProgressDialogFragment(context: Context) : AlertDialog(context) {

    companion object {
        fun newInstance(theContext: Context) = ProgressDialogFragment(theContext)
    }

    private val binding: LayoutProgressDialogBinding by lazy { LayoutProgressDialogBinding.inflate(layoutInflater) }

    override fun show() {
        super.show()
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)
    }
}
