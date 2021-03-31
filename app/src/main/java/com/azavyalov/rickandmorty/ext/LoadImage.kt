package com.azavyalov.rickandmorty.ext

import android.widget.ImageView
import com.azavyalov.rickandmorty.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadFromUrl(url: String) {

    var options = RequestOptions()
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .dontAnimate()
        .dontTransform();

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher_round)
        .apply(options)
        .into(this)
}