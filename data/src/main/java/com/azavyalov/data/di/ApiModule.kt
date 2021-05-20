package com.azavyalov.data.di

import com.azavyalov.data.api.ApiCreator
import com.azavyalov.data.api.CharactersApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/** API модуль */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideCharacterApi(): CharactersApi {
        return ApiCreator().create()
    }
}
