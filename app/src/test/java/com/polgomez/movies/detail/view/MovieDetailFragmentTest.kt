package com.polgomez.movies.detail.view

import android.widget.ImageView
import android.widget.TextView
import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.polgomez.core.view.ImageLoader
import com.polgomez.movies.BuildConfig
import com.polgomez.movies.MoviesActivity
import com.polgomez.movies.R
import com.polgomez.movies.detail.MovieDetailContract
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
class MovieDetailFragmentTest {

    @get:Rule
    val rule = robolectricDaggerMockRule()

    val state: MovieDetailContract.State = mock()

    val presenter: MovieDetailContract.Presenter = mock()

    val moviesStory: MoviesStory = spy(MoviesStory(mock(), MoviesState()))

    val imageLoader : ImageLoader = mock()

    private lateinit var fragment: MovieDetailFragment

    @Before
    fun setUp() {
        // Disabling user story behaviour to enable fragment isolated test
        doNothing().whenever(moviesStory).start()

        fragment = MovieDetailFragment()
        SupportFragmentTestUtil.startVisibleFragment(fragment, MoviesActivity::class.java, R.id.container)
    }

    @Test
    fun `should create view`() {
        whenever(state.getCurrentMovie()).thenReturn(mock())
        assert(fragment.view != null)
    }

    @Test
    fun `should set text to title text view`() {
        fragment.showMovieTitle("Fake title")

        assert(fragment.view?.findViewById<TextView>(R.id.titleText)?.text == "Fake title")
    }

    @Test
    fun `should set first air date to year text view`() {
        fragment.showMovieYear("Fake year")

        assert(fragment.view?.findViewById<TextView>(R.id.yearText)?.text == "Fake year")
    }

    @Test
    fun `should set description text to description text view`() {
        fragment.showMovieDescription("Fake description")

        assert(fragment.view?.findViewById<TextView>(R.id.descriptionText)?.text == "Fake description")
    }

    @Test
    fun `should use image loader to load movie image url into image view`(){
        fragment.showMovieBigImage("Fake url")

        verify(imageLoader).loadImage(fragment.view?.findViewById<ImageView>(R.id.collapsingImageView)!!, "Fake url")
    }

    @Test
    fun `should initialize presenter`() {
        verify(presenter).attachView(fragment)
        verify(presenter).start()
    }
}