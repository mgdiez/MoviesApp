package com.polgomez.movies.filter.di

import com.polgomez.core.di.scope.PerFragment
import com.polgomez.movies.filter.MoviesFilterContract
import com.polgomez.movies.filter.presenter.MovieFilterPresenter
import com.polgomez.movies.story.MoviesState
import com.polgomez.movies.story.MoviesStory
import dagger.Module
import dagger.Provides

@Module
class MoviesFilterModule {

    @Provides
    @PerFragment
    fun provideState(moviesState: MoviesState): MoviesFilterContract.State = moviesState

    @Provides
    @PerFragment
    fun provideNavigation(moviesStory: MoviesStory): MoviesFilterContract.Navigation = moviesStory

    @Provides
    @PerFragment
    fun providePresenter(
        state: MoviesFilterContract.State,
        navigation: MoviesFilterContract.Navigation
    ): MoviesFilterContract.Presenter = MovieFilterPresenter(state, navigation)
}
