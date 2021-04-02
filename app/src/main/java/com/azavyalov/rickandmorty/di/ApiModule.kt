package com.azavyalov.rickandmorty.di

import com.azavyalov.rickandmorty.data.remote.CharactersApi
import com.azavyalov.rickandmorty.data.remote.CharactersService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideCharacterApi(): CharactersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(buildOkHttp())
            .build()
            .create(CharactersApi::class.java)
    }

    @Provides
    fun provideCharacterService(): CharactersService {
        return CharactersService()
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