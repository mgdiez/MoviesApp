package com.polgomez.movies.story

import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.polgomez.core.story.StoryScreen
import com.polgomez.core.story.StoryScreenContainer
import com.polgomez.movies.detail.view.MovieDetailFragment
import com.polgomez.movies.list.view.MovieListFragment
import org.junit.Before
import org.junit.Test

class MoviesStoryTest {

    private val storyScreenContainer: StoryScreenContainer = mock()
    private val storyState: MoviesState = mock()

    private lateinit var moviesStory: MoviesStory

    val screenCaptor: KArgumentCaptor<StoryScreen> = argumentCaptor()

    @Before
    fun setUp() {
        moviesStory = MoviesStory(storyScreenContainer, storyState)
    }

    @Test
    fun `should add any screen to the container on start `() {
        moviesStory.start()

        verify(storyScreenContainer).addStoryScreen(any())
    }

    @Test
    fun `should add movie list on start`() {
        moviesStory.start()

        verify(storyScreenContainer).addStoryScreen(screenCaptor.capture())

        val screen = screenCaptor.firstValue
        assert(screen.fragmentClass == MovieListFragment::class.java)
    }

    @Test
    fun `should replace by movie detail on movie selection`() {
        moviesStory.navigateToMovieDetail(mock())

        verify(storyScreenContainer).replaceStoryScreen(screenCaptor.capture())

        val screen = screenCaptor.firstValue
        assert(screen.fragmentClass == MovieDetailFragment::class.java)
    }
}