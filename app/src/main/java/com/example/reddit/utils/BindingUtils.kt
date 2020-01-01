package com.example.reddit.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingUtils {

    @JvmStatic
    @BindingAdapter("thumbnail")
    fun showThumbnail(imageView: ImageView, thumbnail: String) {
        Picasso.get()
            .load(thumbnail)
            .fit()
            .into(imageView)
    }
}