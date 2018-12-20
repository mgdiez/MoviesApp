package com.polgomez.movies.domain.usecase

import com.polgomez.movies.domain.MoviesRepository
import com.polgomez.movies.domain.bo.MoviesPageResponse
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

class GetMoviesPageUseCase(
    private val moviesRepository: MoviesRepository,
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
) {

    private var subscription: Disposable = Disposables.empty()

    fun execute(page: Int, minYear: String?, maxYear: String?, onComplete: (MoviesPageResponse) -> Unit, onError: (Throwable) -> Unit) {
        subscription = moviesRepository.getMovies(page, minYear, maxYear)
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .subscribe(onComplete, onError)
    }

    fun clear() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }
}
