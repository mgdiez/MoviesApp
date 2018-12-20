package com.polgomez.movies.filter.di

import com.polgomez.movies.filter.view.MoviesFilterFragment
import dagger.Subcomponent

@Subcomponent(modules = [MoviesFilterModule::class])
interface MoviesFilterComponent {

    fun inject(fragment: MoviesFilterFragment)

    @Subcomponent.Builder
    interface Builder {
        fun moviesFilterModule(module: MoviesFilterModule): Builder

        fun build(): MoviesFilterComponent
    }
}