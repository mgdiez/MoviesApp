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

    private var isFiltering = false

    override fun attachView(view: MoviesListContract.View) {
        this.view = view
    }

    override fun start() {
        if (state.getMinYear() == null && state.getMaxYear() == null) {
            isFiltering = false
            state.getMovies()?.let {
                loadMovies(it)
            } ?: run {
                view.showLoading()
                obtainMoviesPage()
            }
        } else {
            isFiltering = true
            view.showLoading()
            obtainMoviesPage()
        }
    }

    private fun loadMovies(moviesList: List<Movie>) = when (getCurrentPage()) {
        1 -> with(view) {
            hideLoading()
            showMovies(moviesList)
        }
        else -> view.showMoreMovies(moviesList)
    }

    private fun obtainMoviesPage() =
        getMoviesPageUseCase.execute(getCurrentPage(), state.getMinYear(), state.getMaxYear(), ::handleMoviesPageResponse, ::handleError)

    private fun getCurrentPage(): Int = if (isFiltering) state.getFilterPage() else state.getPage()

    private fun handleError(throwable: Throwable) {
        if (state.getPage() == 1) view.showError()
        Log.e(TAG, throwable.message)
    }

    private fun handleMoviesPageResponse(moviesPageResponse: MoviesPageResponse) {
        loadMovies(moviesPageResponse.moviesList)
        updateState(moviesPageResponse.moviesList, moviesPageResponse.totalPages)
    }

    private fun updateState(moviesList: List<Movie>, totalPages: Int) {
        if (isFiltering) state.setFilterTotalPages(totalPages) else state.setTotalPages(totalPages)
        state.setTotalPages(totalPages)
        if (state.getMovies() == null) state.setMovies(moviesList)
        else {
            val movies = state.getMovies()!!.toMutableList()
            movies.addAll(moviesList)
            state.setMovies(movies)
        }
        if (hasMorePages()) {
            if (isFiltering) state.setFilterPage(state.getFilterPage() + 1) else state.setPage(state.getPage() + 1)
        }
    }

    override fun stop() {
        getMoviesPageUseCase.clear()
    }

    override fun onMovieClicked(movie: Movie) {
        navigation.navigateToMovieDetail(movie)
    }

    override fun onBottomReached() {
        if (hasMorePages()) obtainMoviesPage()
    }

    private fun hasMorePages() = if(isFiltering) state.getFilterPage() <= state.getFilterTotalPages() else state.getPage() <= state.getTotalPages()

    override fun onRetryClicked() {
        view.hideError()
        obtainMoviesPage()
    }

    override fun onMenuFiltersClicked() {
        navigation.navigateToFilters()
    }

    companion object {
        private val TAG: String = MovieListPresenter::class.java.simpleName
    }
}