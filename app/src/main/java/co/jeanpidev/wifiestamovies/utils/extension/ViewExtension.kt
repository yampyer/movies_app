package co.jeanpidev.wifiestamovies.utils.extension

import android.app.Activity
import android.content.ContextWrapper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import co.jeanpidev.wifiestamovies.R
import co.jeanpidev.wifiestamovies.application.App
import co.jeanpidev.wifiestamovies.utils.API_KEY_HEADER
import co.jeanpidev.wifiestamovies.utils.IMAGES_URL
import co.jeanpidev.wifiestamovies.utils.KEY_TOKEN
import co.jeanpidev.wifiestamovies.utils.SIZE_PARAM
import co.jeanpidev.wifiestamovies.utils.helpers.PreferencesHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

val Activity.app: App
    get() = application as App

fun ImageView.setImage(imageUrl: String?) {
    getParentActivity()?.let {
        imageUrl?.let { url ->
            val glideUrl = GlideUrl(
                IMAGES_URL + url + SIZE_PARAM, LazyHeaders.Builder()
                    .addHeader(API_KEY_HEADER, PreferencesHelper(it.baseContext).get(KEY_TOKEN))
                    .build()
            )
            Glide.with(it.baseContext).load(glideUrl)
                .transition(withCrossFade())
                .placeholder(R.drawable.broken_image)
                .fitCenter()
                .thumbnail(0.5f)
                .into(this)
        } ?: run {
            Glide.with(it.baseContext).load(R.drawable.broken_image)
                .fitCenter()
                .into(this)
        }
    }
}
