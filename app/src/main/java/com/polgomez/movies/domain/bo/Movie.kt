package com.polgomez.movies.domain.bo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val title: String, val description: String?, val imageUrl: String, val bigImageUrl: String) :
    Parcelable