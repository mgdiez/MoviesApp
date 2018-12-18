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
import com.polgomez.movies.list.view.adapter.MoviesListAdapter
import com.polgomez.movies.model.MovieModel
import kotlinx.android.synthetic.main.fragment_movies_list.*
import javax.inject.Inject

class MovieListFragment : Fragment(), MoviesListContract.View {

    @Inject
    lateinit var presenter: MoviesListContract.Presenter

    @Inject
    lateinit var moviesAdapter: MoviesListAdapter

    @Inject
    lateinit var moviesLayoutManager: RecyclerView.LayoutManager

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
        initializePresenter()
    }

    private fun initializePresenter() {
        presenter.attachView(this)
        presenter.start()
    }

    private fun initializeViews() {
        retryButton.setOnClickListener {
            presenter.onRetryClicked()
        }
        with(recyclerView) {
            adapter = moviesAdapter
            layoutManager = moviesLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    if (!canScrollVertically(RecyclerView.VERTICAL)) presenter.onBottomReached()
                }
            })

            setHasFixedSize(true)
        }
        moviesAdapter.setMovieClickListener { movieModel -> presenter.onMovieClicked(movieModel) }
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun showMovies(movies: List<MovieModel>) = moviesAdapter.setMovies(movies)

    override fun addMovies(movies: List<MovieModel>) = moviesAdapter.addMovies(movies)

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
