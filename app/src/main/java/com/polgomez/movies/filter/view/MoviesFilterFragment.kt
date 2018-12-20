package com.polgomez.movies.filter.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.polgomez.movies.MoviesActivity
import com.polgomez.movies.filter.MoviesFilterContract
import com.polgomez.movies.filter.di.MoviesFilterModule

class MoviesFilterFragment : Fragment(), MoviesFilterContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeInjections()
    }

    private fun initializeInjections() {
        val moviesActivity = activity as MoviesActivity
        moviesActivity.moviesActivityComponent.moviesFilterComponent().moviesFilterModule(MoviesFilterModule()).build()
            .inject(this)
    }

    override fun loadMinYear(minYear: String) {
    }

    override fun loadMaxYear(minYear: String) {
    }

    override fun showClearButton() {
    }

    override fun hideClearButton() {
    }

    companion object {

        fun newInstance(): MoviesFilterFragment {

            val args = Bundle()

            val fragment = MoviesFilterFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
