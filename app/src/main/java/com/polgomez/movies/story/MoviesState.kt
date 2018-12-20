package com.polgomez.movies.story

import com.polgomez.core.story.StoryState
import com.polgomez.movies.detail.MovieDetailContract
import com.polgomez.movies.domain.bo.Movie
import com.polgomez.movies.filter.MoviesFilterContract
import com.polgomez.movies.list.MoviesListContract
import kotlinx.android.parcel.Parcelize

@Parcelize
class MoviesState(
    private var stateMovies: List<Movie>? = null,
    private var statePage: Int = 1,
    private var totalPages: Int = 1,
    private var currentMovie: Movie? = null,
    private var minYear: String? = null,
    private var maxYear: String? = null
) : StoryState, MoviesListContract.State, MovieDetailContract.State, MoviesFilterContract.State {

    override fun getMinYear(): String? = minYear

    override fun setMinYear(minYear: String?) {
        this.minYear = minYear
    }

    override fun getMaxYear(): String? = maxYear

    override fun setMaxYear(maxYear: String?) {
        this.maxYear = maxYear
    }

    override fun setTotalPages(page: Int) {
        totalPages = page
    }

    override fun getTotalPages(): Int = totalPages

    override fun getMovies(): List<Movie>? = stateMovies

    override fun setMovies(movies: List<Movie>) {
        stateMovies = movies
    }

    override fun setPage(page: Int) {
        statePage = page
    }

    override fun getPage(): Int = statePage

    override fun getCurrentMovie(): Movie = currentMovie!!

    override fun setSelectedMovie(movie: Movie) {
        currentMovie = movie
    }

    override fun clear() {
        stateMovies = null
        statePage = 1
        totalPages = 1
    }
}
