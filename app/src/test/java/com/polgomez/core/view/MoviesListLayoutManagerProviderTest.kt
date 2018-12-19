package com.polgomez.core.view

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test

class MoviesListLayoutManagerProviderTest {

    @Test
    fun `should provide linear layout manager if is not in landscape`() {
        val provider = MoviesListLayoutManagerProvider(mock(), true)
        assert(provider.getLayoutManager() is GridLayoutManager)
    }

    @Test
    fun `should provide grid layout manager if is in landscape`() {
        val provider = MoviesListLayoutManagerProvider(mock(), false)
        assert(provider.getLayoutManager() is LinearLayoutManager)
    }
}