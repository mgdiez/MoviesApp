package com.polgomez.movies.detail.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.polgomez.movies.detail.MovieDetailContract
import com.polgomez.movies.domain.bo.Movie
import org.junit.Before
import org.junit.Test

class MovieDetailPresenterTest {

    private val state: MovieDetailContract.State = mock()

    private val view: MovieDetailContract.View = mock()

    private lateinit var presenter: MovieDetailContract.Presenter

    @Before
    fun setUp() {
        presenter = MovieDetailPresenter(state).also { it.attachView(view) }
    }

    @Test
    fun `should load current movie from state to the view`() {
        whenever(state.getCurrentMovie()).thenReturn(createFakeMovie())

        presenter.start()

        verify(view).showMovieTitle("FakeMovieTitle")
        verify(view).showMovieDescription("FakeDescription")
        verify(view).showMovieBigImage("FakeBigImageUrl")
        verify(view).showMovieYear("FakeYear")
    }

    private fun createFakeMovie(): Movie {
        return Movie(
            "FakeMovieTitle",
            "FakeDescription",
            "FakeImageUrl",
            "FakeBigImageUrl",
            "FakeYear"
        )
    }
}