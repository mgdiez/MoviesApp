package com.polgomez.movies.di

import android.content.Context
import android.content.res.Configuration
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
import javax.inject.Named

@Module
class MoviesActivityModule(private val moviesActivity: MoviesActivity) {

    @Provides
    @PerActivity
    fun provideContext(): Context = moviesActivity

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

    @Provides
    @PerActivity
    @Named("isLandscape")
    fun provideScreenOrientation(context: Context): Boolean =
        context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}
