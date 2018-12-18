package com.polgomez.movies.list.di

import android.support.v7.widget.RecyclerView
import com.polgomez.core.di.scope.PerFragment
import com.polgomez.core.story.UserStory
import com.polgomez.core.view.ImageLoader
import com.polgomez.movies.list.MoviesListContract
import com.polgomez.movies.list.presenter.MovieListPresenter
import com.polgomez.movies.list.view.adapter.MoviesListAdapter
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
    fun provideAdapter(imageLoader: ImageLoader): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return MoviesListAdapter(imageLoader)
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
