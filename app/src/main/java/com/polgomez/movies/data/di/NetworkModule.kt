package com.polgomez.movies.data.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.polgomez.movies.data.MoviesApi
import com.polgomez.movies.data.MoviesMapper
import com.polgomez.movies.data.NetworkMoviesRepository
import com.polgomez.movies.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
open class NetworkModule {

    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .apply {
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(MoviesApi.BASE_URL)
                client(okHttpClient)
            }
            .build()

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun providesMapper(): MoviesMapper = MoviesMapper()

    @Provides
    fun provideMoviesRepository(moviesApi: MoviesApi, moviesMapper: MoviesMapper): MoviesRepository =
        NetworkMoviesRepository(moviesApi, moviesMapper)
}