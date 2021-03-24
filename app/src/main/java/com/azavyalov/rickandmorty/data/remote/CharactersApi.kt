package com.azavyalov.rickandmorty.data.remote

import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.data.entities.Characters
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {

    @GET("/character")
    fun getCharacters(): Single<Characters>

    @GET("/character/{id}")
    fun getCharacter(@Path("id") id: Int): Single<Character>
}