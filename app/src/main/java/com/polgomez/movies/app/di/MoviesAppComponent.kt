package com.polgomez.movies.app.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MoviesAppModule::class])
interface MoviesAppComponent
