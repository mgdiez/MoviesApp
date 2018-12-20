package com.polgomez.movies.filter.presenter

import com.polgomez.movies.domain.validation.YearStringValidation
import com.polgomez.movies.filter.MoviesFilterContract

class MovieFilterPresenter(
    private var state: MoviesFilterContract.State,
    private var navigation: MoviesFilterContract.Navigation,
    private var yearValidation: YearStringValidation
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
        val isValidMinYear = currentMinYear?.let(yearValidation::isValid) ?: true
        val isValidMaxYear = currentMaxYear?.let(yearValidation::isValid) ?: true

        if (isValidMaxYear && isValidMinYear) {
            state.setMinYear(currentMinYear)
            state.setMaxYear(currentMaxYear)
            state.clear()
            navigation.onFiltersConfirm()
        } else {
            view.showError()

            if (!isValidMaxYear) {
                view.showErrorMaxYear()
            }

            if (!isValidMinYear) {
                view.showErrorMinYear()
            }
        }
    }

    override fun onFiltersCleared() {
        currentMinYear = null
        currentMaxYear = null

        with(view) {
            loadMinYear(currentMinYear)
            loadMaxYear(currentMaxYear)
            hideClearButton()
        }
    }

    override fun onMinYearChanged(minYear: String?) {
        currentMinYear = minYear
        view.hideErrorMinYear()
        updateClearButtonVisibility()
    }

    override fun onMaxYearChanged(maxYear: String?) {
        currentMaxYear = maxYear
        view.hideErrorMaxYear()
        updateClearButtonVisibility()
    }

    private fun updateClearButtonVisibility() =
        if (currentMinYear == null && currentMaxYear == null) view.hideClearButton()
        else with(view) {
            showClearButton()
            showConfirmButton()
        }
}
