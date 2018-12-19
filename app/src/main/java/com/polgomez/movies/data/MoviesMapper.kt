package com.polgomez.movies.data

import com.polgomez.movies.data.dto.GetMoviesResponseDto
import com.polgomez.movies.data.dto.MovieDto
import com.polgomez.movies.domain.bo.Movie
import com.polgomez.movies.domain.bo.MoviesPageResponse

class MoviesMapper {
    fun map(getMoviesResponseDto: GetMoviesResponseDto): MoviesPageResponse =
        MoviesPageResponse(
            moviesDtoToBo(getMoviesResponseDto.results),
            getMoviesResponseDto.total_pages
        )

    private fun moviesDtoToBo(moviesDto: List<MovieDto>): List<Movie> =
        moviesDto.map {
            Movie(
                it.name,
                it.overview,
                generateImageUrl(it.poster_path),
                generateBigImageUrl(it.poster_path)
            )
        }

    private fun generateImageUrl(url: String) =
        ApiImageConfig.API_BASE_IMAGE + ApiImageConfig.API_POSTER_IMAGE_SIZE + url

    private fun generateBigImageUrl(url: String) =
        ApiImageConfig.API_BASE_IMAGE + ApiImageConfig.API_BACKDROP_IMAGE_SIZE + url
}

object ApiImageConfig {
    const val API_BASE_IMAGE = "https://image.tmdb.org/t/p/"
    const val API_POSTER_IMAGE_SIZE = "w500"
    const val API_BACKDROP_IMAGE_SIZE = "w1280"
}
