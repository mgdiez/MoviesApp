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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMovieClicked(movieModel: MovieModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBottomReached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRetryClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}