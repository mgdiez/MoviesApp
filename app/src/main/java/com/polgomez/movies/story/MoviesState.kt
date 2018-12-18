package com.polgomez.movies.story

import com.polgomez.core.story.StoryState
import com.polgomez.movies.list.MoviesListContract
import kotlinx.android.parcel.Parcelize

@Parcelize
class MoviesState : StoryState, MoviesListContract.State {
    override fun getMovies(): List<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMovies(movies: List<*>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPage(page: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPage(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
