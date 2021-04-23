package com.azavyalov.data.repository

import com.azavyalov.data.api.CharactersApi
import com.azavyalov.data.di.DaggerApiComponent
import com.azavyalov.data.models.CharacterDetails
import com.azavyalov.data.models.Characters
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

    override fun getCharacterDetails(id: Int) : Single<CharacterDetails> {
        return api.getCharacterDetails(id)
    }
}
