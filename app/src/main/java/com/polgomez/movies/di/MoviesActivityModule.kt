package com.polgomez.movies.di

import android.support.v4.app.FragmentManager
import com.polgomez.core.di.scope.PerActivity
import com.polgomez.core.story.StoryScreenContainer
import com.polgomez.core.story.UserStory
import com.polgomez.movies.MoviesActivity
import com.polgomez.movies.story.MoviesScreenContainer
import com.polgomez.movies.story.MoviesState
import com.polgomez.movies.story.MoviesStory
import dagger.Module
import dagger.Provides

@Module
class MoviesActivityModule(private val moviesActivity: MoviesActivity) {

    @Provides
    @PerActivity
    fun provideFragmentManager() = moviesActivity.supportFragmentManager

    @Provides
    @PerActivity
    fun provideStoryContainer(fragmentManager: FragmentManager): StoryScreenContainer =
        MoviesScreenContainer(fragmentManager)

    @Provides
    @PerActivity
    fun provideStoryState(): MoviesState = MoviesState()

    @Provides
    @PerActivity
    fun provideUserStory(storyScreenContainer: StoryScreenContainer, storyState: MoviesState): UserStory<*> =
        MoviesStory(storyScreenContainer, storyState)
}
