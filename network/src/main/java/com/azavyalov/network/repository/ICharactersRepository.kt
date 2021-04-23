package com.azavyalov.network.repository

import com.azavyalov.network.models.Character
import com.azavyalov.network.models.Characters
import io.reactivex.Single

interface ICharactersRepository {

    fun getCharacters(page: String): Single<Characters>

    fun getCharacterDetails(id: Int): Single<Character>
}
