package com.azavyalov.data.di

import com.azavyalov.data.repository.CharactersRepository
import com.azavyalov.data.repository.EpisodesRepository
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(repository: CharactersRepository)

    fun inject(repository: EpisodesRepository)
}
