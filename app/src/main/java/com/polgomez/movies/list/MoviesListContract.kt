package com.polgomez.movies.list

import com.polgomez.movies.model.MovieModel

interface MoviesListContract {
    interface View {
        fun showMovies(movies: List<MovieModel>)
        fun addMovies(movies: List<MovieModel>)
        fun showLoading()
        fun hideLoading()
        fun showError()
        fun hideError()
    }

    interface Presenter {
        fun attachView(view: MoviesListContract.View)
        fun start()
        fun stop()
        fun onMovieClicked(movieModel: MovieModel)
        fun onBottomReached()
        fun onRetryClicked()
    }

    interface State {
        fun getMovies(): List<MovieModel>?
        fun setMovies(movies: List<MovieModel>)
        fun setPage(page: Int)
        fun getPage(): Int
    }

    interface Navigation {
        fun navigateToMovieDetail()
        fun navigateToFilters()
    }
}