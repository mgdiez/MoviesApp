package com.polgomez

import android.support.v4.app.FragmentManager
import com.nhaarman.mockito_kotlin.mock
import com.polgomez.movies.app.di.MoviesAppComponent
import com.polgomez.movies.app.di.MoviesAppModule
import it.cosenonjaviste.daggermock.DaggerMock

fun jUnitDaggerMockRule() = DaggerMock.rule<MoviesAppComponent>(MoviesAppModule(mock())) {
    providesMock<FragmentManager>()
}