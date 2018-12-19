package com.polgomez.movies.data

import com.polgomez.movies.data.dto.GetMoviesResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale

interface MoviesApi {

    @GET("tv/popular")
    fun getMoviesList(
        @Query("api_key") api: String = API_KEY,
        @Query("language") language: String = Locale.getDefault().language,
        @Query("page") page: Int
    ): Single<GetMoviesResponseDto>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "27a83e2c7fa92b559841e7ee676b766c"
    }
}