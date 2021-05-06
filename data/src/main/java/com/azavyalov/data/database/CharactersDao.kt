package com.azavyalov.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azavyalov.data.models.CharacterDetails
import io.reactivex.Single

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters")
    fun getCharacters(): Single<List<CharacterDetails>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): Single<CharacterDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<CharacterDetails>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: CharacterDetails)
}