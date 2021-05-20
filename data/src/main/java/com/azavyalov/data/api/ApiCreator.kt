package com.azavyalov.data.api

import com.azavyalov.data.api.interceptors.DebugHttpLoggingInterceptor
import com.azavyalov.data.api.interceptors.PlatformHeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/** Класс для формирования API Rick and Morty */
class ApiCreator {

    fun create(): CharactersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(buildOkHttp())
            .build()
            .create(CharactersApi::class.java)
    }

    private fun buildOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(PlatformHeaderInterceptor())
            .addInterceptor(DebugHttpLoggingInterceptor())
            .build()
    }

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com"
    }
}