package com.internal.model

import com.squareup.moshi.Json

/**
 * Model for school details API
 */
data class SchoolDetailsList(val errorMessage: String?, val schoolDetailsList: List<SchoolDetails?>?)

data class SchoolDetails(
    @field:Json(name = "dbn") val dbn: String?,
    @field:Json(name = "school_name") val schoolName: String?,
    @field:Json(name = "num_of_sat_test_takers") val testTaker: String?,
    @field:Json(name = "sat_critical_reading_avg_score") val reading: String?,
    @field:Json(name = "sat_writing_avg_score") val writing: String?,
    @field:Json(name = "sat_math_avg_score") val math: String?
)
