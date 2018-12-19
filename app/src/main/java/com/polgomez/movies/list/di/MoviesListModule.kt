package com.polgomez.movies.list.di

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.polgomez.core.di.scope.PerFragment
import com.polgomez.core.story.UserStory
import com.polgomez.core.view.ImageLoader
import com.polgomez.core.view.MoviesListLayoutManagerProvider
import com.polgomez.movies.domain.MoviesRepository
import com.polgomez.movies.domain.bo.Movie
import com.polgomez.movies.domain.bo.MoviesPageResponse
import com.polgomez.movies.domain.usecase.GetMoviesPageUseCase
import com.polgomez.movies.list.MoviesListContract
import com.polgomez.movies.list.presenter.MovieListPresenter
import com.polgomez.movies.list.view.adapter.MoviesListAdapter
import com.polgomez.movies.story.MoviesState
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Named

@Module
class MoviesListModule() {

    @Provides
    @PerFragment
    fun provideState(moviesState: MoviesState): MoviesListContract.State = moviesState

    @Provides
    @PerFragment
    fun provideNavigation(userStory: UserStory<*>): MoviesListContract.Navigation =
        userStory as MoviesListContract.Navigation

    @Provides
    @PerFragment
    fun provideAdapter(imageLoader: ImageLoader): MoviesListAdapter = MoviesListAdapter(imageLoader)

    @Provides
    @PerFragment
    fun providePresenter(
        state: MoviesListContract.State,
        navigation: MoviesListContract.Navigation,
        getMoviesPageUseCase: GetMoviesPageUseCase
    ): MoviesListContract.Presenter = MovieListPresenter(state, navigation, getMoviesPageUseCase)

    @Provides
    @PerFragment
    fun provideLayoutManagerProvider(context: Context, @Named("isLandscape") isLandscape: Boolean): RecyclerView.LayoutManager =
        MoviesListLayoutManagerProvider(context, isLandscape).getLayoutManager()

    @Provides
    @PerFragment
    fun provideGetMoviesPageUseCase(@Named("subscribeOn") subscribeOn: Scheduler, @Named("observeOn") observeOn: Scheduler): GetMoviesPageUseCase {
        return GetMoviesPageUseCase(object : MoviesRepository {
            override fun getMovies(page: Int): Single<MoviesPageResponse> {
                return Single.just(MoviesPageResponse(listOf(Movie("Fake Title", "null")), 1))
            }
        }, observeOn, subscribeOn)
    }
}
