package com.polgomez.movies.detail.di

import com.polgomez.core.di.scope.PerFragment
import com.polgomez.movies.detail.MovieDetailContract
import com.polgomez.movies.story.MoviesState
import dagger.Module
import dagger.Provides

@Module
class MoviesDetailModule {

    @Provides
    @PerFragment
    fun providePresenter(): MovieDetailContract.Presenter {
        TODO()
    }

    @Provides
    @PerFragment
    fun provideState(moviesState: MoviesState): MovieDetailContract.State = moviesState
}

