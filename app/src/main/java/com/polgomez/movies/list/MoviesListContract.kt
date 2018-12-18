package com.polgomez.movies.list

interface MoviesListContract {
    interface View {
        fun showMovies(movies: List<*>)
        fun addMovies(movies: List<*>)
        fun showLoading()
        fun hideLoading()
        fun showError()
        fun hideError()
    }

    interface Presenter {
        fun start()
        fun stop()
        fun onMovieClicked()
        fun onBottomReached()
        fun onRetryClicked()
    }

    interface State {
        fun getMovies(): List<*>
        fun setMovies(movies: List<*>)
        fun setPage(page: Int)
        fun getPage(): Int
    }

    interface Navigation {
        fun onMovieSelected()
    }
}