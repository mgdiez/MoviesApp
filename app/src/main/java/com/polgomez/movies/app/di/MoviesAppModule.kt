package com.polgomez.movies.app.di

import com.polgomez.core.view.ImageLoader
import com.polgomez.core.view.PicassoMovieImageLoader
import com.polgomez.movies.app.MoviesApp
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
open class MoviesAppModule(private val moviesApp: MoviesApp) {

    @Provides
    @Singleton
    fun provideImageLoader(): ImageLoader = PicassoMovieImageLoader()

    @Provides
    @Singleton
    @Named("observeOn")
    fun observeOnScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named("subscribeOn")
    fun subscribeOnScheduler(): Scheduler = Schedulers.io()
}
