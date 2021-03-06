package com.polgomez.movies.filter.view

import android.view.View
import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.polgomez.movies.BuildConfig
import com.polgomez.movies.MoviesActivity
import com.polgomez.movies.R
import com.polgomez.movies.filter.MoviesFilterContract
import com.polgomez.movies.story.MoviesState
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
class MoviesFilterFragmentTest {

    @get:Rule
    val rule = robolectricDaggerMockRule()

    val presenter: MoviesFilterContract.Presenter = mock()

    val moviesStory: MoviesStory = spy(MoviesStory(mock(), MoviesState()))

    private lateinit var fragment: MoviesFilterFragment

    @Before
    fun setUp() {
        // Disabling user story behaviour to enable fragment isolated test
        doNothing().whenever(moviesStory).start()

        fragment = MoviesFilterFragment()
        SupportFragmentTestUtil.startVisibleFragment(fragment, MoviesActivity::class.java, R.id.container)
    }

    @Test
    fun `should create view`() {
        assert(fragment.view != null)
    }

    @Test
    fun `should initialize presenter`() {
        verify(presenter).attachView(fragment)
        verify(presenter).start()
    }

    @Test
    fun `should hide buttons on invocation`() {
        fragment.hideConfirmButton()

        val view = fragment.view?.findViewById<View>(R.id.buttonsLayout)
        assert(view?.visibility == View.GONE)
    }

    @Test
    fun `should show buttons on invocation`() {
        fragment.showConfirmButton()

        val view = fragment.view?.findViewById<View>(R.id.buttonsLayout)
        assert(view?.visibility == View.VISIBLE)
    }

    @Test
    fun `should show clear button on invocation`() {
        fragment.showClearButton()

        val view = fragment.view?.findViewById<View>(R.id.clearButton)
        assert(view?.visibility == View.VISIBLE)
    }

    @Test
    fun `should hide clear button on invocation`() {
        fragment.hideClearButton()

        val view = fragment.view?.findViewById<View>(R.id.clearButton)
        assert(view?.visibility == View.GONE)
    }

    @Test
    fun `should show error view on invocation`() {
        fragment.showError()

        val view = fragment.view?.findViewById<View>(R.id.errorView)
        assert(view?.visibility == View.VISIBLE)
    }

    @Test
    fun `should hide error view on invocation`() {
        fragment.hideError()

        val view = fragment.view?.findViewById<View>(R.id.errorView)
        assert(view?.visibility == View.GONE)
    }

    @Test
    fun `should delegate confirm click to presenter`() {
        fragment.view?.findViewById<View>(R.id.confirmButton)?.performClick()

        verify(presenter).onFiltersConfirmed()
    }

    @Test
    fun `should delegate clear click to presenter`() {
        fragment.showClearButton()
        fragment.view?.findViewById<View>(R.id.clearButton)?.performClick()

        verify(presenter).onFiltersCleared()
    }
}