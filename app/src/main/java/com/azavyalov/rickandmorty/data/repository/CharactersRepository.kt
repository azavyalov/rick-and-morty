package com.azavyalov.rickandmorty.data.repository

import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.data.remote.CharactersApi
import com.azavyalov.rickandmorty.data.remote.CharactersResponse
import com.azavyalov.rickandmorty.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CharactersRepository : ICharactersRepository {

    @Inject
    lateinit var api: CharactersApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    override fun getCharacters(page: String): Single<CharactersResponse> {
        return api.getCharacters(page)
    }

    override fun getCharacterDetails(id: Int) : Single<Character> {
        return api.getCharacterDetails(id)
    }
}