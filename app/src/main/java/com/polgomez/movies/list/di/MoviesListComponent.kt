package com.polgomez.movies.list.di

import com.polgomez.core.di.scope.PerFragment
import com.polgomez.movies.list.view.MovieListFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [MoviesListModule::class])
interface MoviesListComponent {

    fun inject(fragment: MovieListFragment)

    @Subcomponent.Builder
    interface Builder {
        fun moviesListModule(module: MoviesListModule): Builder

        fun build(): MoviesListComponent
    }
}
