package com.azavyalov.data.di

import com.azavyalov.data.api.CharactersApi
import com.azavyalov.data.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/** API модуль */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideCharacterApi(): CharactersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(buildOkHttp())
            .build()
            .create(CharactersApi::class.java)
    }

    private fun buildOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com"
    }
}
