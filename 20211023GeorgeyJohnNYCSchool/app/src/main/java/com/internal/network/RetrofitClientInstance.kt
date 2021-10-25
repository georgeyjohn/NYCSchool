package com.internal.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.reflect.KClass

class RetrofitClientInstance {
    val BASE_URL =  "https://data.cityofnewyork.us/resource/"

    /**
     * Retrofit Instance
     */
    inline fun <reified T : Any> getRetrofitInstance(clazz: KClass<T>): T? {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(T::class.java)
    }
}


