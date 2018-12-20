package com.polgomez.movies.story

import com.polgomez.core.story.StoryScreen
import com.polgomez.core.story.StoryScreenContainer
import com.polgomez.core.story.UserStory
import com.polgomez.movies.detail.view.MovieDetailFragment
import com.polgomez.movies.domain.bo.Movie
import com.polgomez.movies.filter.MoviesFilterContract
import com.polgomez.movies.list.MoviesListContract
import com.polgomez.movies.list.view.MovieListFragment

class MoviesStory(storyScreenContainer: StoryScreenContainer, state: MoviesState = MoviesState()) :
    UserStory<MoviesState>(storyScreenContainer, state), MoviesListContract.Navigation,
    MoviesFilterContract.Navigation {

    override fun start() {
        storyScreenContainer.addStoryScreen(StoryScreen(MovieListFragment::class.java))
    }

    override fun navigateToMovieDetail(movie: Movie) {
        state.setSelectedMovie(movie)
        storyScreenContainer.replaceStoryScreen(StoryScreen(MovieDetailFragment::class.java))
    }

    override fun navigateToFilters() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun onFiltersConfirm() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
