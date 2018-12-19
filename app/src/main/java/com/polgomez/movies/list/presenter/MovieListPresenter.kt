package com.polgomez.movies.list.presenter

import android.util.Log
import com.polgomez.movies.domain.bo.Movie
import com.polgomez.movies.domain.bo.MoviesPageResponse
import com.polgomez.movies.domain.usecase.GetMoviesPageUseCase
import com.polgomez.movies.list.MoviesListContract

class MovieListPresenter(
    private val state: MoviesListContract.State,
    private val navigation: MoviesListContract.Navigation,
    private val getMoviesPageUseCase: GetMoviesPageUseCase
) : MoviesListContract.Presenter {

    lateinit var view: MoviesListContract.View

    override fun attachView(view: MoviesListContract.View) {
        this.view = view
    }

    override fun start() = state.getMovies()?.let {
        loadMovies(it)
    } ?: run {
        view.showLoading()
        obtainMoviesPage()
    }

    private fun loadMovies(moviesList: List<Movie>) = when (state.getPage()) {
        1 -> with(view) {
            hideLoading()
            showMovies(moviesList)
        }
        else -> view.showMoreMovies(moviesList)
    }

    private fun obtainMoviesPage() {
        getMoviesPageUseCase.execute(state.getPage(), ::handleMoviesPageResponse, ::handleError)
    }

    private fun handleError(throwable: Throwable) {
        if (state.getPage() == 1) view.showError()
        Log.e(TAG, throwable.message)
    }

    private fun handleMoviesPageResponse(moviesPageResponse: MoviesPageResponse) {
        loadMovies(moviesPageResponse.moviesList)
        updateState(moviesPageResponse.moviesList, moviesPageResponse.totalPages)
    }

    private fun updateState(moviesList: List<Movie>, totalPages: Int) {
        val currentPage = state.getPage()
        if (state.getMovies() == null) {
            state.setMovies(moviesList)
        } else {
            val movies = state.getMovies()!!.toMutableList()
            movies.addAll(moviesList)
            state.setMovies(movies)
        }
        if (currentPage <= totalPages) {
            state.setPage(currentPage + 1)
        }
    }

    override fun stop() {
        getMoviesPageUseCase.clear()
    }

    override fun onMovieClicked(movie: Movie) {
        navigation.navigateToMovieDetail(movie)
    }

    override fun onBottomReached() {
        if (state.getPage() <= state.getTotalPages()) obtainMoviesPage()
    }

    override fun onRetryClicked() {
        view.hideError()
        obtainMoviesPage()
    }

    companion object {
        private val TAG: String = MovieListPresenter::class.java.simpleName
    }
}