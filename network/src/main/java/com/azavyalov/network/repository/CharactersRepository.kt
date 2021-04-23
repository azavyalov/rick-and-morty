package com.azavyalov.network.repository

import com.azavyalov.network.api.CharactersApi
import com.azavyalov.network.di.DaggerApiComponent
import com.azavyalov.network.models.Character
import com.azavyalov.network.models.Characters
import io.reactivex.Single
import javax.inject.Inject

class CharactersRepository : ICharactersRepository {

    @Inject
    lateinit var api: CharactersApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    override fun getCharacters(page: String): Single<Characters> {
        return api.getCharacters(page)
    }

    override fun getCharacterDetails(id: Int) : Single<Character> {
        return api.getCharacterDetails(id)
    }
}
