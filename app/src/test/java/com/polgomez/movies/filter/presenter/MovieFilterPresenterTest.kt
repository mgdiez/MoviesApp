package com.polgomez.movies.filter.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.polgomez.movies.filter.MoviesFilterContract
import com.polgomez.movies.story.MoviesState
import org.junit.Before
import org.junit.Test

class MovieFilterPresenterTest {

    private val state: MoviesFilterContract.State = spy(MoviesState())

    private val navigation: MoviesFilterContract.Navigation = mock()

    private val view: MoviesFilterContract.View = mock()

    private val presenter: MoviesFilterContract.Presenter = MovieFilterPresenter(state, navigation)

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
    }

    @Test
    fun `should clear current filter values when filters are cleared`() {
        presenter.onFiltersCleared()

        verify(view).loadMinYear(null)
        verify(view).loadMaxYear(null)
    }
}