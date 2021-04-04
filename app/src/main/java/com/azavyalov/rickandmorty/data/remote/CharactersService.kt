package com.azavyalov.rickandmorty.data.remote

import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CharactersService {

    @Inject
    lateinit var api: CharactersApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCharacters(pageNumber: String): Single<CharactersResponse> {
        return api.getCharacters(pageNumber)
    }

    fun getCharacterDetails(id: Int) : Single<Character> {
        return api.getCharacterDetails(id)
    }
}