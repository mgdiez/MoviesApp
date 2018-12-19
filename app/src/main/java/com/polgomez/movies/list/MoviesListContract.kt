package com.polgomez.movies.list

import com.polgomez.movies.domain.bo.Movie

interface MoviesListContract {
    interface View {
        fun showMovies(movies: List<Movie>)
        fun showMoreMovies(movies: List<Movie>)
        fun showLoading()
        fun hideLoading()
        fun showError()
        fun hideError()
    }

    interface Presenter {
        fun attachView(view: MoviesListContract.View)
        fun start()
        fun stop()
        fun onMovieClicked(movie: Movie)
        fun onBottomReached()
        fun onRetryClicked()
    }

    interface State {
        fun getMovies(): List<Movie>?
        fun setMovies(movies: List<Movie>)
        fun setPage(page: Int)
        fun getPage(): Int
    }

    interface Navigation {
        fun navigateToMovieDetail()
        fun navigateToFilters()
    }
}