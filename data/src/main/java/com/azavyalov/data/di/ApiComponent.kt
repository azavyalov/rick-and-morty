package com.azavyalov.data.di

import com.azavyalov.data.repository.CharactersRepository
import com.azavyalov.data.repository.EpisodesRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class])
@Singleton
interface ApiComponent {

    fun inject(repository: CharactersRepository)

    fun inject(repository: EpisodesRepository)
}
