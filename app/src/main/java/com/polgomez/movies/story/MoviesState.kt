package com.polgomez.movies.story

import com.polgomez.core.story.StoryState
import com.polgomez.movies.list.MoviesListContract
import com.polgomez.movies.model.MovieModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class MoviesState(private var stateMovies: List<MovieModel>? = null, private var statePage: Int = 0) : StoryState,
    MoviesListContract.State {

    override fun getMovies(): List<MovieModel>? = stateMovies

    override fun setMovies(movies: List<MovieModel>) {
        stateMovies = movies
    }

    override fun setPage(page: Int) {
        statePage = page
    }

    override fun getPage(): Int = statePage
}
