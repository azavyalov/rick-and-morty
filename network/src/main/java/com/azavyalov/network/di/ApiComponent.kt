package com.azavyalov.network.di

import com.azavyalov.network.repository.CharactersRepository
import com.azavyalov.network.repository.EpisodesRepository
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(repository: CharactersRepository)

    fun inject(repository: EpisodesRepository)
}
