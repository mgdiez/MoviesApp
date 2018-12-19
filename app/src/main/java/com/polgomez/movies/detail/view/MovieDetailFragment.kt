package com.polgomez.movies.detail.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.polgomez.movies.R
import com.polgomez.movies.detail.MovieDetailContract
import com.polgomez.movies.domain.bo.Movie

class MovieDetailFragment : Fragment(), MovieDetailContract.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_movie_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        initializePresenter()
    }

    private fun initializePresenter() {
    }

    private fun initializeViews() {
    }

    override fun showMovieDetails(movie: Movie) {
    }

    override fun showTitleShow(title: String) {
    }

    companion object {

        fun newInstance(): MovieDetailFragment {

            val args = Bundle()

            val fragment = MovieDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
