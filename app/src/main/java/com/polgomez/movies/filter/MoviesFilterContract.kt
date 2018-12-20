package com.polgomez.movies.filter

interface MoviesFilterContract {

    interface View {
        fun loadMinYear(minYear: String)

        fun loadMaxYear(maxYear: String)

        fun showClearButton()

        fun hideClearButton()

        fun showConfirmButton()

        fun hideConfirmButton()

        fun showError()

        fun hideError()
    }

    interface Presenter {
        fun attachView(view: View)

        fun start()

        fun onFiltersConfirmed()

        fun onFiltersCleared()
    }

    interface State {
        fun getMinYear(): String?

        fun setMinYear(minYear: String?)

        fun getMaxYear(): String?

        fun setMaxYear(minYear: String?)
    }

    interface Navigation {
        fun onFiltersConfirm()
    }
}