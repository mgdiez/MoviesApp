package com.polgomez.movies.data

import com.polgomez.movies.domain.MoviesRepository
import com.polgomez.movies.domain.bo.MoviesPageResponse
import io.reactivex.Single

class NetworkMoviesRepository(private val moviesApi: MoviesApi, private val moviesMapper: MoviesMapper) :
    MoviesRepository {

    override fun getMovies(page: Int): Single<MoviesPageResponse> =
        moviesApi.getMoviesList(page = page).map { moviesMapper.map(it) }
}