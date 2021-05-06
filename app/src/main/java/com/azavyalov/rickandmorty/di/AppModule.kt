package com.azavyalov.rickandmorty.di

import com.azavyalov.data.repository.CharactersRepository
import com.azavyalov.data.repository.EpisodesRepository
import com.azavyalov.rickandmorty.ui.characters.CharactersViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideCharacterRepository(): CharactersRepository {
        return CharactersRepository()
    }

    @Provides
    fun provideEpisodeRepository(): EpisodesRepository {
        return EpisodesRepository()
    }
}