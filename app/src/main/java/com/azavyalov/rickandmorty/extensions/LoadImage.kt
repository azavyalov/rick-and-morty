package com.azavyalov.rickandmorty.extensions

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

fun AppCompatImageView.loadFromUrl(url: String) {
    Glide.with(context)
        .load(url)
        .transform(CircleCrop())
        .into(this)
}

@BindingAdapter("android:loadUrl")
fun loadImage(view: AppCompatImageView, url: String?) {
    url?.let {
        view.loadFromUrl(it)
    }
}
