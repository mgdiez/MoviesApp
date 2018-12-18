package com.polgomez.movies.story

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.polgomez.core.story.StoryScreenContainer
import org.junit.Before
import org.junit.Test

class MoviesStoryTest {

    private val storyScreenContainer: StoryScreenContainer = mock()
    private val storyState: MoviesState = mock()

    private lateinit var moviesStory: MoviesStory

    @Before
    fun setUp() {
        moviesStory = MoviesStory(storyScreenContainer, storyState)
    }

    @Test
    fun `should add any screen to the container on start `() {
        moviesStory.start()
        
        verify(storyScreenContainer).addStoryScreen(any())
    }
}