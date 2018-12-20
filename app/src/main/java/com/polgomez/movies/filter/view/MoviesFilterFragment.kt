package com.polgomez.movies.filter.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.polgomez.core.extensions.hide
import com.polgomez.core.extensions.show
import com.polgomez.movies.MoviesActivity
import com.polgomez.movies.R
import com.polgomez.movies.filter.MoviesFilterContract
import com.polgomez.movies.filter.di.MoviesFilterModule
import kotlinx.android.synthetic.main.fragment_movies_filter.*
import javax.inject.Inject

class MoviesFilterFragment : Fragment(), MoviesFilterContract.View {

    @Inject
    lateinit var presenter: MoviesFilterContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_movies_filter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeInjections()
        initializeViews()
        initializePresenter()
        initializeActionBar()
    }

    private fun initializeActionBar() {
        (activity as? AppCompatActivity)?.let {
            it.setSupportActionBar(toolbar)
            it.supportActionBar?.let { actionBar ->
                actionBar.setDisplayShowTitleEnabled(false)
                actionBar.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    private fun initializeViews() {
        confirmButton.setOnClickListener { presenter.onFiltersConfirmed() }
        clearButton.setOnClickListener { presenter.onFiltersCleared() }
    }

    private fun initializePresenter() {
        presenter.attachView(this)
        presenter.start()
    }

    private fun initializeInjections() {
        val moviesActivity = activity as MoviesActivity
        moviesActivity.moviesActivityComponent.moviesFilterComponent().moviesFilterModule(MoviesFilterModule()).build()
            .inject(this)
    }

    override fun loadMinYear(minYear: String?) = minYearTextInput.setText(minYear)

    override fun loadMaxYear(maxYear: String?) = maxYearTextInput.setText(maxYear)

    override fun showClearButton() = clearButton.show()

    override fun hideClearButton() = clearButton.hide()

    override fun showConfirmButton() = buttonsLayout.show()

    override fun hideConfirmButton() = buttonsLayout.hide()

    override fun showError() = errorView.show()

    override fun hideError() = errorView.hide()

    companion object {

        fun newInstance(): MoviesFilterFragment {

            val args = Bundle()

            val fragment = MoviesFilterFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
