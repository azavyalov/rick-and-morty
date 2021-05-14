package com.azavyalov.data.repository

import com.azavyalov.data.models.CharacterDetails
import com.azavyalov.data.models.Characters
import io.reactivex.Single

/** Интерфейс репозитория получения данных о персонажах */
interface ICharactersRepository {

    fun getCharacters(page: String): Single<Characters>

    fun getCharacterDetails(id: Int): Single<CharacterDetails>
}
