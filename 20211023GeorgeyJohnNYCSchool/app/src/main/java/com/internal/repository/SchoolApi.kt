package com.internal.repository

import com.internal.model.School
import com.internal.model.SchoolList
import com.internal.network.RetrofitClientInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface SchoolApi {
    @GET("s3k6-pzi2.json")
    suspend fun getSchoolList(): List<School>
}

/**
 * This is the Repository class
 * The method getSchoolList to get the School List from API call
 * Here we can use Repository to access Data from a Repository instead of fetching data from API(Future)
 */
class SchoolRepository {
    fun getSchoolList(): Flow<SchoolList?> {
        return flow {
            val schoolList =
                RetrofitClientInstance().getRetrofitInstance(SchoolApi::class)?.getSchoolList()
            emit(SchoolList(errorMessage = "", schoolList))
        }.catch {
            emit(SchoolList(errorMessage = it.message, listOf()))
        }
    }
}