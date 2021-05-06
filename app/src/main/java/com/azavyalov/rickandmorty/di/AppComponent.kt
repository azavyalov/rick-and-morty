package com.azavyalov.rickandmorty.di

import com.azavyalov.rickandmorty.ui.characters.CharactersViewModel
import com.azavyalov.rickandmorty.ui.details.CharacterDetailsViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(charactersViewModel : CharactersViewModel)

    fun inject(characterDetailsViewModel : CharacterDetailsViewModel)
}