package com.polgomez.movies.app.di

import com.polgomez.core.view.ImageLoader
import com.polgomez.core.view.PicassoImageLoader
import com.polgomez.movies.app.MoviesApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesAppModule(private val moviesApp: MoviesApp){

    @Provides
    @Singleton
    fun provideImageLoader() : ImageLoader = PicassoImageLoader()
}
