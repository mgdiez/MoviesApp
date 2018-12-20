package com.polgomez.movies.domain.validation

import org.junit.Test

class YearStringValidationTest {

    @Test
    fun `should return false if year is minor than first ever film`() {
        assert(!YearStringValidation().isValid("1600"))
    }

    @Test
    fun `should return false if year is major than current year`() {
        assert(!YearStringValidation().isValid("2022"))
    }

    @Test
    fun `should return true if year is in range`() {
        assert(YearStringValidation().isValid("2000"))
    }
}