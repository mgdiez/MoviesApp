package com.polgomez.movies.filter.presenter

import com.polgomez.movies.filter.MoviesFilterContract

class MovieFilterPresenter(
    private var state: MoviesFilterContract.State,
    private var navigation: MoviesFilterContract.Navigation
) : MoviesFilterContract.Presenter {

    lateinit var view: MoviesFilterContract.View

    override fun attachView(view: MoviesFilterContract.View) {
        this.view = view
    }

    private var currentMinYear: String? = null
    private var currentMaxYear: String? = null

    override fun start() = with(view) {
        val hasMinYear = state.getMinYear()?.let {
            currentMinYear = it
            loadMinYear(it)
            true
        } ?: false

        val hasMaxYear = state.getMaxYear()?.let {
            loadMaxYear(it)
            true
        } ?: false

        when {
            hasMinYear || hasMaxYear -> {
                showConfirmButton()
                showClearButton()
            }
            else -> hideConfirmButton()
        }
    }

    override fun onFiltersConfirmed() {
        state.setMinYear(currentMinYear)
        state.setMaxYear(currentMaxYear)

        navigation.onFiltersConfirm()
    }

    override fun onFiltersCleared() {
        currentMinYear = null
        currentMaxYear = null

        with(view) {
            loadMinYear(currentMinYear)
            loadMaxYear(currentMaxYear)
        }
    }

    override fun onMinYearChanged(minYear: String?) {
        currentMinYear = minYear
    }

    override fun onMaxYearChanged(maxYear: String?) {
       currentMaxYear = maxYear
    }
}
