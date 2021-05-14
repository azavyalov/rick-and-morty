package com.azavyalov.rickandmorty.di

import com.azavyalov.data.repository.CharactersRepository
import com.azavyalov.data.repository.EpisodesRepository
import com.azavyalov.rickandmorty.ui.characters.CharactersViewModel
import com.azavyalov.rickandmorty.ui.details.CharacterDetailsViewModel
import dagger.Module
import dagger.Provides

/** Модуль приложения */
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

    @Provides
    fun provideCharactersViewModel(): CharactersViewModel {
        return CharactersViewModel()
    }

    @Provides
    fun provideCharactersDetailsViewModel(): CharacterDetailsViewModel {
        return CharacterDetailsViewModel()
    }
}