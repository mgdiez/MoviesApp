package com.polgomez.movies.detail

import com.polgomez.movies.domain.bo.Movie

interface MovieDetailContract {
    interface View {
        fun showMovieBigImage(imageUrl: String)

        fun showMovieDescription(description: String)

        fun showMovieYear(movieYear: String)

        fun showTitleShow(title: String)
    }

    interface Presenter {
        fun attachView(view: View)

        fun start()
    }

    interface State {
        fun getCurrentMovie(): Movie
    }
}