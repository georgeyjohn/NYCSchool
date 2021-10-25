package com.internal.model

import com.squareup.moshi.Json

/**
 * Model for School list from API call
 */

data class SchoolList(val errorMessage: String?, val schoolList: List<School?>?)

data class School(@field:Json(name = "school_name") val schoolName: String?,
                  @field:Json(name = "dbn") val dbn: String?,
                  @field:Json(name = "overview_paragraph") val overView: String?,
                  @field:Json(name = "website") val website: String?,
                  @field:Json(name = "phone_number") val phone: String?,
                  @field:Json(name = "latitude") val lat: String?,
                  @field:Json(name = "longitude") val lon: String?)
