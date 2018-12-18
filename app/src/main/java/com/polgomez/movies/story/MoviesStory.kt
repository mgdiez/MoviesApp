package com.polgomez.movies.story

import android.support.v4.app.Fragment
import com.polgomez.core.story.StoryScreen
import com.polgomez.core.story.StoryScreenContainer
import com.polgomez.core.story.UserStory

class MoviesStory(storyScreenContainer: StoryScreenContainer, state: MoviesState) :
    UserStory<MoviesState>(storyScreenContainer, state) {

    override fun start() {
        storyScreenContainer.addStoryScreen(StoryScreen(Fragment::class.java))
    }
}