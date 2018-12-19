package com.polgomez.core.view

import android.widget.ImageView
import com.polgomez.movies.R
import com.squareup.picasso.Picasso

interface ImageLoader {

    fun loadImage(imageView: ImageView, url: String)
}

class PicassoMovieImageLoader : ImageLoader {
    override fun loadImage(
        imageView: ImageView,
        url: String
    ) {
        Picasso.get().load(url).placeholder(R.drawable.movie_placeholder).into(imageView)
    }
}