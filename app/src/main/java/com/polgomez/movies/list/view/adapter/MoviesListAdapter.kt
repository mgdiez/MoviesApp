package com.polgomez.movies.list.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.polgomez.core.extensions.inflate
import com.polgomez.core.view.ImageLoader
import com.polgomez.movies.R
import com.polgomez.movies.domain.bo.Movie

class MoviesListAdapter(private val imageLoader: ImageLoader) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: MutableList<Movie> = ArrayList()

    private var movieListener: ((Movie) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent.inflate(viewType))

    override fun getItemCount(): Int = movies.size

    override fun getItemViewType(position: Int): Int = R.layout.item_movie

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = with(holder) {
        val movieModel = movies[position]
        imageLoader.loadImage(movieImageView, movieModel.imageUrl)
        titleText.text = movieModel.title
        itemView.setOnClickListener {
            movieListener?.invoke(movieModel)
        }
    }

    fun setMovieClickListener(listener: (Movie) -> Unit) {
        movieListener = listener
    }

    fun setMovies(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    fun addMovies(movies: List<Movie>) {
        val previousSize = this.movies.size
        this.movies.addAll(movies)
        notifyItemRangeInserted(previousSize, this.movies.size)
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val movieImageView: ImageView = itemView.findViewById(R.id.movieImageView)
    val titleText: TextView = itemView.findViewById(R.id.titleText)
}
