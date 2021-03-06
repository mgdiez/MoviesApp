package com.polgomez.movies.data.dto

data class MovieDto(
    val backdrop_path: String,
    val release_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val title: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String?,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)
