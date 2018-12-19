package com.polgomez.movies.list.presenter

import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.polgomez.movies.domain.MoviesRepository
import com.polgomez.movies.domain.bo.Movie
import com.polgomez.movies.domain.bo.MoviesPageResponse
import com.polgomez.movies.domain.usecase.GetMoviesPageUseCase
import com.polgomez.movies.list.MoviesListContract
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

class MovieListPresenterTest {

    private val testScheduler = TestScheduler()

    private val moviesRepository: MoviesRepository = mock()

    private val getMoviesPageUseCase: GetMoviesPageUseCase =
        spy(GetMoviesPageUseCase(moviesRepository, testScheduler, testScheduler))

    private val state: MoviesListContract.State = mock()

    private val navigation: MoviesListContract.Navigation = mock()

    private val view: MoviesListContract.View = mock()

    private lateinit var presenter: MovieListPresenter

    private val movieListCaptor: KArgumentCaptor<List<Movie>> = argumentCaptor<List<Movie>>()

    @Before
    fun setUp() {
        presenter = MovieListPresenter(state, navigation, getMoviesPageUseCase)
        presenter.attachView(view)
    }

    @Test
    fun `should obtain movies if don't have state`() {
        givenStateMovies()
        givenStatePage(1)
        givenMoviesPageResponse()

        presenter.start()

        verify(view).showLoading()
        verify(getMoviesPageUseCase).execute(eq(1), any(), any())
    }

    @Test
    fun `should load state movies if has first page`() {
        val movies = listOf(Movie("FakeMovieTitle", "FakeDescription", "", ""))
        givenStateMovies(movies)
        givenStatePage(1)

        presenter.start()

        verify(view).hideLoading()
        verify(view).showMovies(movies)
    }

    @Test
    fun `should update view after obtaining movies page on start`() {
        givenStateMovies()
        givenStatePage(1)
        val movies = listOf(Movie("FakeMovieTitle", "FakeDescription", "", ""))
        givenMoviesPageResponse(movies)

        presenter.start().run {
            testScheduler.triggerActions()
        }

        verify(view).showMovies(movies)
    }

    @Test
    fun `should update state after obtaining movies page on start`() {
        givenStateMovies()
        givenStatePage(1)
        val movies = listOf(Movie("FakeMovieTitle", "FakeDescription", "", ""))
        givenMoviesPageResponse(movies, 400)

        presenter.start().run {
            testScheduler.triggerActions()
        }

        verify(state).setMovies(movies)
        verify(state).setPage(2)
        verify(state).setTotalPages(400)
    }

    @Test
    fun `should obtain movies page after bottom reached if has pages`() {
        givenStateMovies(emptyList())
        givenStatePage(2)
        givenStateTotalPages(3)
        val movies = listOf(Movie("FakeMovieTitle", "FakeDescription", "", ""))
        givenMoviesPageResponse(movies)

        presenter.onBottomReached()

        verify(getMoviesPageUseCase).execute(eq(2), any(), any())
    }

    @Test
    fun `should update view after obtaining paginated movies`() {
        givenStateMovies(listOf(Movie("FakeMovieTitle", "FakeDescription", "", "")))
        givenStatePage(2)
        givenStateTotalPages(3)
        val movies = listOf(
            Movie("FakeMovieTitle", "FakeDescription", "", ""),
            Movie("FakeMovieTitle", "FakeDescription", "", "")
        )
        givenMoviesPageResponse(movies)

        presenter.onBottomReached().run { testScheduler.triggerActions() }

        verify(view).showMoreMovies(movieListCaptor.capture())

        val movieList = movieListCaptor.firstValue
        assert(movieList.size == 2)
    }

    @Test
    fun `should update state after obtaining paginated movies`() {
        givenStateMovies(listOf(Movie("FakeMovieTitle", "FakeDescription", "", "")))
        givenStatePage(2)
        givenStateTotalPages(3)
        val movies = listOf(
            Movie("FakeMovieTitle", "FakeDescription", "", ""),
            Movie("FakeMovieTitle", "FakeDescription", "", "")
        )
        givenMoviesPageResponse(movies)

        presenter.onBottomReached().run { testScheduler.triggerActions() }

        verify(state).setMovies(movieListCaptor.capture())

        val movieList = movieListCaptor.firstValue
        assert(movieList.size == 3)
    }

    @Test
    fun `should not obtain movies page after bottom reached if has pages`() {
        givenStateMovies(emptyList())
        givenStatePage(4)
        givenStateTotalPages(3)
        givenMoviesPageResponse()

        presenter.onBottomReached()

        verify(getMoviesPageUseCase, never()).execute(eq(2), any(), any())
    }

    @Test
    fun `should not update current page if has obtained all pages on start`() {
        givenStateMovies()
        givenStateTotalPages(1)
        givenStatePage(2)
        givenMoviesPageResponse()

        presenter.start()

        verify(state, never()).setPage(any())
    }

    @Test
    fun `should show error only if is in the first page`() {
        givenStateMovies()
        givenStateTotalPages(1)
        givenStatePage(1)
        whenever(moviesRepository.getMovies(any())).thenReturn(Single.error(Exception()))

        presenter.start().run { testScheduler.triggerActions() }

        verify(view).showError()
    }

    @Test
    fun `should not show error if is in pagination`() {
        givenStateMovies()
        givenStateTotalPages(3)
        givenStatePage(2)
        whenever(moviesRepository.getMovies(any())).thenReturn(Single.error(Exception()))

        presenter.onBottomReached().run { testScheduler.triggerActions() }

        verify(view, never()).showError()
    }

    @Test
    fun `should hide error and obtain movies first page on retry click`() {
        givenStatePage(1)
        givenMoviesPageResponse()

        presenter.onRetryClicked()

        verify(view).hideError()
        verify(getMoviesPageUseCase).execute(eq(1), any(), any())
    }

    @Test
    fun `should clear use case execution on stop`() {
        presenter.stop()

        verify(getMoviesPageUseCase).clear()
    }

    @Test
    fun `should trigger navigation to detail on movie selection`() {
        presenter.onMovieClicked(mock())

        verify(navigation).navigateToMovieDetail(any())
    }

    private fun givenStatePage(page: Int = 1) = whenever(state.getPage()).thenReturn(page)

    private fun givenStateTotalPages(page: Int = 2) = whenever(state.getTotalPages()).thenReturn(page)

    private fun givenStateMovies(movies: List<Movie>? = null) = whenever(state.getMovies()).thenReturn(movies)

    private fun givenMoviesPageResponse(movies: List<Movie> = emptyList(), page: Int = 1) =
        whenever(moviesRepository.getMovies(any())).thenReturn(Single.just(MoviesPageResponse(movies, page)))
}