package com.polgomez.movies.detail

import com.polgomez.movies.domain.bo.Movie

interface MovieDetailContract {
    interface View {
        fun showMovieDetails(movie: Movie)

        fun showTitleShow(title: String)
    }

    interface Presenter {
        fun attachView(view: View)

        fun start()
    }
}