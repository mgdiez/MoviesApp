package com.polgomez.movies.domain

import com.polgomez.movies.domain.bo.MoviesPageResponse
import io.reactivex.Single

interface MoviesRepository {
    fun getMovies(page: Int, minYear: String?, maxYear: String?): Single<MoviesPageResponse>
}
