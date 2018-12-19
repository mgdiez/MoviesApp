package com.polgomez.movies.list.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.polgomez.core.story.UserStory
import com.polgomez.movies.BuildConfig
import com.polgomez.movies.MoviesActivity
import com.polgomez.movies.R
import com.polgomez.movies.list.MoviesListContract
import com.polgomez.movies.list.view.adapter.MoviesListAdapter
import com.polgomez.movies.domain.bo.Movie
import com.polgomez.movies.story.MoviesStory
import com.polgomez.robolectricDaggerMockRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21], constants = BuildConfig::class)
class MovieListFragmentTest {

    @get:Rule
    val rule = robolectricDaggerMockRule()

    val presenter: MoviesListContract.Presenter = mock()

    val movieStory: MoviesStory = mock()

    val userStory: UserStory<*> = movieStory

    val moviesListAdapter: MoviesListAdapter = spy(MoviesListAdapter(mock()))

    private lateinit var fragment: MovieListFragment

    @Before
    fun setUp() {
        // Disabling user story behaviour to enable fragment isolated test
        doNothing().whenever(userStory).start()

        fragment = MovieListFragment()
        SupportFragmentTestUtil.startVisibleFragment(fragment, MoviesActivity::class.java, R.id.container)
    }

    @Test
    fun `should create view`() {
        assert(fragment.view != null)
    }

    @Test
    fun `should bind lifecycle to presenter`() {
        fragment.onStop()

        verify(presenter).attachView(fragment)
        verify(presenter).start()
        verify(presenter).stop()
        verifyNoMoreInteractions(presenter)
    }

    @Test
    fun `should hide error view on method invocation`() {
        fragment.hideError()

        val errorLayout = fragment.view?.findViewById<View>(R.id.errorLayout)
        assert(errorLayout?.visibility == View.GONE)
    }

    @Test
    fun `should show error view on method invocation`() {
        fragment.showError()

        val errorLayout = fragment.view?.findViewById<View>(R.id.errorLayout)
        assert(errorLayout?.visibility == View.VISIBLE)
    }

    @Test
    fun `should set movies to the adapter`() {
        fragment.showMovies(emptyList())

        verify(moviesListAdapter).setMovies(any())
    }

    @Test
    fun `should add movies to the adapter`() {
        fragment.showMoreMovies(emptyList())

        verify(moviesListAdapter).addMovies(any())
    }

    @Test
    fun `should hide progress on method invocation`() {
        fragment.hideLoading()

        val progressView = fragment.view?.findViewById<View>(R.id.progressView)
        assert(progressView?.visibility == View.GONE)
    }

    @Test
    fun `should show progress on method invocation`() {
        fragment.showLoading()

        val progressView = fragment.view?.findViewById<View>(R.id.progressView)
        assert(progressView?.visibility == View.VISIBLE)
    }

    @Test
    fun `should delegate retry click to presenter`() {
        fragment.showError()

        fragment.view?.findViewById<View>(R.id.retryButton)?.performClick()

        verify(presenter).onRetryClicked()
    }

    @Test
    fun `should render movie models`() {
        fragment.showMovies(
            listOf(
                Movie("firstMovieTitle", "fakeUrl"),
                Movie("secondMovieTitle", "fakeUrl")
            )
        )

        val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.recyclerView)
        val itemCount = recyclerView?.adapter!!.itemCount
        assert(itemCount == 2)
    }

    @Test
    fun `should render paginated movie models`() {
        fragment.showMovies(
            listOf(
                Movie("firstMovieTitle", "fakeUrl"),
                Movie("secondMovieTitle", "fakeUrl")
            )
        )
        fragment.showMoreMovies(
            listOf(
                Movie("thirdMovieTitle", "fakeUrl"),
                Movie("fourthMovieTitle", "fakeUrl")
            )
        )

        val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.recyclerView)
        val itemCount = recyclerView?.adapter!!.itemCount
        assert(itemCount == 4)
    }
}