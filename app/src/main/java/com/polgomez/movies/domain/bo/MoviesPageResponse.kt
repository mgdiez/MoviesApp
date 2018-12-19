package com.polgomez.movies.domain.bo

data class MoviesPageResponse(
    val moviesList: List<Movie>,
    val totalPages: Int
)
