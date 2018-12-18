package com.polgomez.movies.list.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.polgomez.movies.list.MoviesListContract

class MovieListFragment : Fragment(), MoviesListContract.View {

    override fun showMovies(movies: List<*>) {
    }

    override fun addMovies(movies: List<*>) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError() {
    }

    override fun hideError() {
    }

    companion object {

        fun newInstance(): MovieListFragment {
            val args = Bundle()

            val fragment = MovieListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
