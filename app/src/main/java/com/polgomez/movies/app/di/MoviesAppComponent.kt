package com.polgomez.movies.app.di

import com.polgomez.movies.di.MoviesActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MoviesAppModule::class])
interface MoviesAppComponent {
    fun moviesActivityComponentBuilder(): MoviesActivityComponent.Builder
}
