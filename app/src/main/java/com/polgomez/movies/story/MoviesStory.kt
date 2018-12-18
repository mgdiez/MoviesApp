package com.polgomez.movies.story

import com.polgomez.core.story.StoryScreen
import com.polgomez.core.story.StoryScreenContainer
import com.polgomez.core.story.UserStory
import com.polgomez.movies.list.MoviesListContract
import com.polgomez.movies.list.view.MovieListFragment

class MoviesStory(storyScreenContainer: StoryScreenContainer, state: MoviesState) :
    UserStory<MoviesState>(storyScreenContainer, state), MoviesListContract.Navigation {

    override fun start() {
        storyScreenContainer.addStoryScreen(StoryScreen(MovieListFragment::class.java))
    }

    override fun navigateToMovieDetail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToFilters() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
