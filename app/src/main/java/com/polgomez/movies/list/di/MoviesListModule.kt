package com.polgomez.movies.list.di

import com.polgomez.core.di.scope.PerFragment
import com.polgomez.core.story.UserStory
import com.polgomez.movies.list.MoviesListContract
import com.polgomez.movies.list.presenter.MovieListPresenter
import com.polgomez.movies.story.MoviesState
import dagger.Module
import dagger.Provides

@Module
class MoviesListModule() {

    @Provides
    @PerFragment
    fun provideState(moviesState: MoviesState): MoviesListContract.State {
        return moviesState
    }

    @Provides
    @PerFragment
    fun provideNavigation(userStory: UserStory<*>): MoviesListContract.Navigation {
        return userStory as MoviesListContract.Navigation
    }

    @Provides
    @PerFragment
    fun providePresenter(
        state: MoviesListContract.State,
        navigation: MoviesListContract.Navigation
    ): MoviesListContract.Presenter {
        return MovieListPresenter(state, navigation)
    }
}
