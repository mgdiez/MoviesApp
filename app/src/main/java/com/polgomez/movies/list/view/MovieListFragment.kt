package com.polgomez.movies.list.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.polgomez.movies.MoviesActivity
import com.polgomez.movies.list.MoviesListContract
import com.polgomez.movies.list.di.MoviesListModule

class MovieListFragment : Fragment(), MoviesListContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeInjections()
    }

    private fun initializeInjections() {
        val moviesActivity = activity as MoviesActivity
        moviesActivity.moviesActivityComponent.moviesListComponentBuilder().moviesListModule(MoviesListModule()).build()
            .inject(this)
    }

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
