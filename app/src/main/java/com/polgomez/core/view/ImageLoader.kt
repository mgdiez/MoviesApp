package com.polgomez.core.view

import android.widget.ImageView
import com.squareup.picasso.Picasso

interface ImageLoader {

    fun loadImage(imageView: ImageView, url: String)
}

class PicassoImageLoader : ImageLoader {
    override fun loadImage(imageView: ImageView, url: String) {
        Picasso.get().load(url).into(imageView)
    }
}