package com.polgomez.movies.list.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.polgomez.core.extensions.hide
import com.polgomez.core.extensions.show
import com.polgomez.movies.MoviesActivity
import com.polgomez.movies.R
import com.polgomez.movies.list.MoviesListContract
import com.polgomez.movies.list.di.MoviesListModule
import kotlinx.android.synthetic.main.fragment_movies_list.*
import javax.inject.Inject

class MovieListFragment : Fragment(), MoviesListContract.View {

    @Inject
    lateinit var presenter: MoviesListContract.Presenter

    @Inject
    lateinit var movieAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeInjections()
    }

    private fun initializeInjections() {
        val moviesActivity = activity as MoviesActivity
        moviesActivity.moviesActivityComponent.moviesListComponentBuilder().moviesListModule(MoviesListModule()).build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
    }

    private fun initializeViews() {
        retryButton.setOnClickListener {
            presenter.onRetryClicked()
        }
        with(recyclerView) {
            adapter = movieAdapter
            setHasFixedSize(true)
        }
    }

    override fun showMovies(movies: List<*>) {
    }

    override fun addMovies(movies: List<*>) {
    }

    override fun showLoading() = progressView.show()

    override fun hideLoading() = progressView.hide()

    override fun showError() = errorLayout.show()

    override fun hideError() = errorLayout.hide()

    companion object {

        fun newInstance(): MovieListFragment {
            val args = Bundle()

            val fragment = MovieListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
