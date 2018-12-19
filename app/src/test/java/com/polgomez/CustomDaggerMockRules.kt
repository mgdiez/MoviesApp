package com.polgomez

import com.nhaarman.mockito_kotlin.mock
import com.polgomez.movies.app.MoviesApp
import com.polgomez.movies.app.di.MoviesAppComponent
import com.polgomez.movies.app.di.MoviesAppModule
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import it.cosenonjaviste.daggermock.DaggerMock
import org.robolectric.RuntimeEnvironment

fun jUnitDaggerMockRule() = DaggerMock.rule<MoviesAppComponent>(MoviesAppModule(mock())) {
    provides<Scheduler>(TestScheduler())
}

fun robolectricDaggerMockRule() = DaggerMock.rule<MoviesAppComponent>(MoviesAppModule(mock())) {
    set { (RuntimeEnvironment.application as MoviesApp).component = it }
}