package com.azavyalov.data.di

import android.content.Context
import com.azavyalov.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = AppDatabase.getDatabase(context)

    @Provides
    fun provideCharactersDao(database: AppDatabase) = database.characterDao()
}