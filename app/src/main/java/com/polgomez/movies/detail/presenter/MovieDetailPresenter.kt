package com.polgomez.movies.detail.presenter

import com.polgomez.movies.detail.MovieDetailContract

class MovieDetailPresenter(private val state: MovieDetailContract.State) : MovieDetailContract.Presenter {

    private lateinit var view: MovieDetailContract.View

    override fun attachView(view: MovieDetailContract.View) {
        this.view = view
    }

    override fun start() {
        with(state.getCurrentMovie()) {
            with(view) {
                showMovieDescription(description)
                showMovieYear(year)
                showMovieBigImage(imageUrl)
                showMovieTitle(title)
            }
        }
    }
}
