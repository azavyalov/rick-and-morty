package com.azavyalov.rickandmorty.data.remote

import com.azavyalov.rickandmorty.data.entities.Characters
import com.azavyalov.rickandmorty.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CharactersService {

    @Inject
    lateinit var api: CharactersApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCharacters(): Single<Characters> {
        return api.getCharacters()
    }
}