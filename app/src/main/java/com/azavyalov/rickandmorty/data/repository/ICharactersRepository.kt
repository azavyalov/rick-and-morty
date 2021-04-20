package com.azavyalov.rickandmorty.data.repository

import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.data.remote.CharactersResponse
import io.reactivex.Single

interface ICharactersRepository {

    fun getCharacters(page: String): Single<CharactersResponse>

    fun getCharacterDetails(id: Int): Single<Character>
}
