package com.polgomez.movies.filter.presenter

import com.polgomez.movies.filter.MoviesFilterContract

class MovieFilterPresenter(
    private var state: MoviesFilterContract.State,
    private var navigation: MoviesFilterContract.Navigation
) : MoviesFilterContract.Presenter {

    lateinit var view: MoviesFilterContract.View

    override fun attachView(view: MoviesFilterContract.View) {
        this.view = view
    }

    override fun start() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun onFiltersConfirmed() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun onFiltersCleared() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
