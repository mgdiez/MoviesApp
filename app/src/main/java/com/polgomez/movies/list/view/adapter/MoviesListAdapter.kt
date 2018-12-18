package com.polgomez.movies.list.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.polgomez.core.view.ImageLoader

class MoviesListAdapter(private val imageLoader: ImageLoader) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var movies: MutableList<Any> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setMovies(movies: List<Any>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    fun addMovies(movies: List<Any>) {
        val previousSize = this.movies.size
        this.movies.addAll(movies)
        notifyItemRangeInserted(previousSize, this.movies.size)
    }
}
