package com.internal.repository

import com.internal.model.SchoolDetails
import com.internal.model.SchoolDetailsList
import com.internal.network.RetrofitClientInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolDetailsApi {
    @GET("f9bf-2cp4.json")
    suspend fun getSchoolDetailsList(@Query("dbn") dbn: String?): List<SchoolDetails>
}

/**
 * This is the Repository class
 * The method getSchoolDetails to get the School Details from API call
 * Here we can use Repository to access Data from a Repository instead of fetching data from API(Future)
 */

class SchoolDetailsRepository {
    fun getSchoolDetailsList(dbn: String?): Flow<SchoolDetailsList?> {
        return flow {
            val schoolDetailsList =
                RetrofitClientInstance().getRetrofitInstance(SchoolDetailsApi::class)
                    ?.getSchoolDetailsList(dbn)
            emit(SchoolDetailsList(errorMessage = "", schoolDetailsList))
        }.catch {
            emit(SchoolDetailsList(errorMessage = it.message, listOf()))
        }
    }
}