package com.polgomez.movies.detail.di

import com.polgomez.core.di.scope.PerFragment
import com.polgomez.movies.detail.view.MovieDetailFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [MovieDetailModule::class])
interface MovieDetailComponent {

    fun inject(fragment: MovieDetailFragment)

    @Subcomponent.Builder
    interface Builder {
        fun moviesDetailModule(module: MovieDetailModule): Builder

        fun build(): MovieDetailComponent
    }
}