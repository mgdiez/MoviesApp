package com.polgomez.movies.detail.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.polgomez.core.view.ImageLoader
import com.polgomez.movies.MoviesActivity
import com.polgomez.movies.R
import com.polgomez.movies.detail.MovieDetailContract
import com.polgomez.movies.detail.di.MovieDetailModule
import javax.inject.Inject

class MovieDetailFragment : Fragment(), MovieDetailContract.View {

    @Inject
    lateinit var presenter: MovieDetailContract.Presenter

    @Inject
    lateinit var imageLoader: ImageLoader
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeInjections()
    }

    private fun initializeInjections() {
        val moviesActivity = activity as MoviesActivity
        moviesActivity.moviesActivityComponent.movieDetailComponentBuilder()
            .moviesDetailModule(MovieDetailModule())
            .build()
            .inject(this)
    }

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

    override fun showMovieBigImage(imageUrl: String) {
    }

    override fun showMovieDescription(description: String) {
    }

    override fun showMovieYear(movieYear: String) {
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
