package com.azavyalov.rickandmorty.di

import com.azavyalov.rickandmorty.data.repository.CharactersRepository
import com.azavyalov.rickandmorty.data.repository.EpisodesRepository
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(repository: CharactersRepository)

    fun inject(repository: EpisodesRepository)
}