package com.polgomez.movies.list.presenter

import com.polgomez.movies.list.MoviesListContract
import com.polgomez.movies.model.MovieModel

class MovieListPresenter(
    private val state: MoviesListContract.State,
    private val navigation: MoviesListContract.Navigation
) : MoviesListContract.Presenter {

    lateinit var view: MoviesListContract.View

    override fun attachView(view: MoviesListContract.View) {
        this.view = view
    }

    override fun start() {
    }

    override fun stop() {
    }

    override fun onMovieClicked(movieModel: MovieModel) {
    }

    override fun onBottomReached() {
    }

    override fun onRetryClicked() {
    }
}