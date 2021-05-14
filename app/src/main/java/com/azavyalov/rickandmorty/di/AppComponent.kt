package com.azavyalov.rickandmorty.di

import com.azavyalov.rickandmorty.ui.characters.CharactersFragment
import com.azavyalov.rickandmorty.ui.characters.CharactersViewModel
import com.azavyalov.rickandmorty.ui.details.CharacterDetailsFragment
import com.azavyalov.rickandmorty.ui.details.CharacterDetailsViewModel
import dagger.Component

/** App компонент */
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(charactersViewModel : CharactersViewModel)

    fun inject(characterDetailsViewModel : CharacterDetailsViewModel)

    fun inject(charactersFragment: CharactersFragment)

    fun inject(characterDetailsFragment: CharacterDetailsFragment)
}