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
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject

class MovieDetailFragment : Fragment(), MovieDetailContract.View {

    @Inject
    lateinit var presenter: MovieDetailContract.Presenter

    @Inject
    lateinit var imageLoader: ImageLoader

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
        initializeInjections()
        initializePresenter()
    }

    private fun initializePresenter() = presenter.apply {
        attachView(this@MovieDetailFragment)
        start()
    }

    override fun showMovieBigImage(imageUrl: String) {
        imageLoader.loadImage(collapsingImageView, imageUrl)
    }

    override fun showMovieDescription(description: String) {
        descriptionText.text = description
    }

    override fun showMovieYear(movieYear: String) {
        yearText.text = movieYear
    }

    override fun showMovieTitle(title: String) {
        titleText.text = title
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
