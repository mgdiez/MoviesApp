package com.polgomez.core.view

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

interface LayoutManagerProvider {
    fun getLayoutManager(): RecyclerView.LayoutManager
}

class MoviesListLayoutManagerProvider(private val context: Context, private val isLandscape: Boolean) :
    LayoutManagerProvider {
    override fun getLayoutManager(): RecyclerView.LayoutManager =
        if (isLandscape) GridLayoutManager(context, 4) else LinearLayoutManager(context)
}