package com.polgomez.movies.story

import com.polgomez.core.story.StoryState
import com.polgomez.movies.domain.bo.Movie
import com.polgomez.movies.list.MoviesListContract
import kotlinx.android.parcel.Parcelize

@Parcelize
class MoviesState(private var stateMovies: List<Movie>? = null, private var statePage: Int = 1) : StoryState,
    MoviesListContract.State {

    override fun getMovies(): List<Movie>? = stateMovies

    override fun setMovies(movies: List<Movie>) {
        stateMovies = movies
    }

    override fun setPage(page: Int) {
        statePage = page
    }

    override fun getPage(): Int = statePage
}
