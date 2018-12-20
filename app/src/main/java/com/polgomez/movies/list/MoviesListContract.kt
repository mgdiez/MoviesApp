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
        fun onMenuFiltersClicked()
    }

    interface State {
        fun getMovies(): List<Movie>?
        fun setMovies(movies: List<Movie>)
        fun setPage(page: Int)
        fun getPage(): Int
        fun setTotalPages(page: Int)
        fun getTotalPages(): Int
        fun setSelectedMovie(movie: Movie)
        fun getMaxYear(): String?
        fun getMinYear(): String?
        fun getFilterPage(): Int
        fun setFilterPage(filterPage: Int)
        fun getFilterTotalPages(): Int
        fun setFilterTotalPages(filterTotalPages: Int)
    }

    interface Navigation {
        fun navigateToMovieDetail(movie: Movie)
        fun navigateToFilters()
    }
}