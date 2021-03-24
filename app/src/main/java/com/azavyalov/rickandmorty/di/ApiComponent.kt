package com.azavyalov.rickandmorty.di

import com.azavyalov.rickandmorty.data.remote.CharactersService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CharactersService)
}