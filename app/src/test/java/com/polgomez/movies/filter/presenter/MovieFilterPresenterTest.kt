package com.polgomez.movies.filter.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.polgomez.movies.domain.validation.YearStringValidation
import com.polgomez.movies.filter.MoviesFilterContract
import com.polgomez.movies.story.MoviesState
import org.junit.Before
import org.junit.Test

class MovieFilterPresenterTest {

    private val state: MoviesFilterContract.State = spy(MoviesState())

    private val navigation: MoviesFilterContract.Navigation = mock()

    private val view: MoviesFilterContract.View = mock()

    private val yearValidation = spy(YearStringValidation())

    private val presenter: MoviesFilterContract.Presenter = MovieFilterPresenter(state, navigation, yearValidation)

    @Before
    fun setUp() {
        presenter.attachView(view)
    }

    @Test
    fun `should load state min year from state and show clear and confirm buttons`() {
        whenever(state.getMinYear()).thenReturn("1900")

        presenter.start()

        verify(state).getMinYear()
        verify(view).loadMinYear("1900")
        verify(view).showConfirmButton()
        verify(view).showClearButton()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `should load state max year from state and show clear and confirm buttons`() {
        whenever(state.getMaxYear()).thenReturn("2018")

        presenter.start()

        verify(state).getMaxYear()
        verify(view).loadMaxYear("2018")
        verify(view).showConfirmButton()
        verify(view).showClearButton()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `should hide buttons if there are no state`() {
        presenter.start()

        verify(state).getMaxYear()
        verify(view).hideConfirmButton()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `should clear state filter when filters are cleared and confirmed`() {
        presenter.onFiltersCleared()
        presenter.onFiltersConfirmed()

        verify(state).setMinYear(null)
        verify(state).setMaxYear(null)
        verify(state).clear()
    }

    @Test
    fun `should clear current filter values when filters are cleared and hides button`() {
        presenter.onFiltersCleared()

        verify(view).loadMinYear(null)
        verify(view).loadMaxYear(null)
        verify(view).hideClearButton()
    }

    @Test
    fun `should update state with entered values and navigate when filters are confirmed`() {
        presenter.onMinYearChanged("1900")
        presenter.onMaxYearChanged(null)

        presenter.onFiltersConfirmed()

        verify(state).setMinYear("1900")
        verify(state).setMaxYear(null)
        verify(state).clear()
        verify(navigation).onFiltersConfirm()
    }

    @Test
    fun `should show buttons if max year is entered and clear error`() {
        presenter.onMaxYearChanged("2018")

        verify(view).showConfirmButton()
        verify(view).showClearButton()
        verify(view).hideErrorMaxYear()
    }

    @Test
    fun `should show buttons if min year is entered and clear error`() {
        presenter.onMinYearChanged("2010")

        verify(view).showConfirmButton()
        verify(view).showClearButton()
        verify(view).hideErrorMinYear()
    }

    @Test
    fun `should validate introduced years if are valid navigates`() {
        presenter.onMinYearChanged("2009")
        presenter.onMaxYearChanged("2011")

        presenter.onFiltersConfirmed()

        verify(yearValidation).isValid("2009")
        verify(yearValidation).isValid("2011")
        verify(navigation).onFiltersConfirm()
    }

    @Test
    fun `should validate introduced years if are any invalid show error`() {
        presenter.onMinYearChanged("1")
        presenter.onMaxYearChanged("2022")

        presenter.onFiltersConfirmed()

        verify(yearValidation).isValid("1")
        verify(yearValidation).isValid("2022")
        verify(view).showError()
        verify(navigation, never()).onFiltersConfirm()
    }
}