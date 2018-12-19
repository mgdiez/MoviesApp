package com.polgomez.movies.di

import com.polgomez.core.di.scope.PerActivity
import com.polgomez.movies.MoviesActivity
import com.polgomez.movies.list.di.MoviesListComponent
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [MoviesActivityModule::class])
interface MoviesActivityComponent {

    fun inject(moviesActivity: MoviesActivity)

    @Subcomponent.Builder
    interface Builder {
        fun moviesActivityModule(module: MoviesActivityModule): Builder

        fun build(): MoviesActivityComponent
    }

    fun moviesListComponentBuilder(): MoviesListComponent.Builder
}
