package com.polgomez.movies.filter.di

import com.polgomez.core.di.scope.PerFragment
import com.polgomez.movies.filter.view.MoviesFilterFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [MoviesFilterModule::class])
interface MoviesFilterComponent {

    fun inject(fragment: MoviesFilterFragment)

    @Subcomponent.Builder
    interface Builder {
        fun moviesFilterModule(module: MoviesFilterModule): Builder

        fun build(): MoviesFilterComponent
    }
}