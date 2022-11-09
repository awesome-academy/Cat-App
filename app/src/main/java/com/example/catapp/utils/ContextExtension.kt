package com.example.catapp.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.catapp.R

fun Context.shortToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.loadImage(image: Any, view: ImageView, circleState: Boolean) {
    val requestOptions =
        RequestOptions().transform(CenterCrop(), RoundedCorners(BREED_IMAGE_RADIUS))
    if (circleState) {
        Glide.with(this).load(image).error(R.drawable.feed_me).centerCrop()
            .placeholder(R.drawable.feed_me)
            .transition(DrawableTransitionOptions.withCrossFade(DURATION_FADING))
            .apply(requestOptions).into(view)
    } else {
        Glide.with(this).load(image).centerCrop().placeholder(R.drawable.feed_me)
            .transition(DrawableTransitionOptions.withCrossFade(DURATION_FADING))
            .error(R.drawable.feed_me).into(view)
    }
}

fun Context.loadCoverImage(image: Any, view: ImageView) {
    Glide.with(this).load(image).placeholder(R.drawable.feed_me)
        .transition(DrawableTransitionOptions.withCrossFade(DURATION_FADING))
        .error(R.drawable.feed_me).into(view)
}

fun Context.loadBigImage(image: Any, view: ImageView) {
    Glide.with(this).load(image)
        .transition(DrawableTransitionOptions.withCrossFade(DURATION_FADING))
        .error(R.drawable.feed_me).fitCenter().into(view)
}
