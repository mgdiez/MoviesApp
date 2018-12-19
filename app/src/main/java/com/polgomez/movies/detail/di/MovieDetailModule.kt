package com.polgomez.movies.detail.di

import com.polgomez.core.di.scope.PerFragment
import com.polgomez.movies.detail.MovieDetailContract
import com.polgomez.movies.detail.presenter.MovieDetailPresenter
import com.polgomez.movies.story.MoviesState
import dagger.Module
import dagger.Provides

@Module
class MovieDetailModule {

    @Provides
    @PerFragment
    fun providePresenter(state: MovieDetailContract.State): MovieDetailContract.Presenter {
        return MovieDetailPresenter(state)
    }

    @Provides
    @PerFragment
    fun provideState(moviesState: MoviesState): MovieDetailContract.State = moviesState
}
