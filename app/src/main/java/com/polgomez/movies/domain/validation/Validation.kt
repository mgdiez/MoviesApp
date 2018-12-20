package com.polgomez.movies.domain.validation

interface Validation<T> {
    fun isValid(value: T): Boolean
}

class YearStringValidation : Validation<String> {

    override fun isValid(value: String): Boolean = try {
        value.toInt() in MIN_YEAR..MAX_YEAR
    } catch (e: Exception) {
        false
    }

    companion object {
        const val MIN_YEAR = 1888
        const val MAX_YEAR = 2018
    }
}