package com.polgomez.movies.app

import android.app.Application
import android.support.annotation.VisibleForTesting
import com.polgomez.movies.app.di.DaggerMoviesAppComponent
import com.polgomez.movies.app.di.MoviesAppComponent
import com.polgomez.movies.app.di.MoviesAppModule

class MoviesApp : Application() {

    @set:VisibleForTesting
    lateinit var component: MoviesAppComponent

    override fun onCreate() {
        super.onCreate()
        component = createComponent()
    }

    private fun createComponent(): MoviesAppComponent {
        return DaggerMoviesAppComponent.builder().moviesAppModule(MoviesAppModule(this)).build()
    }
}