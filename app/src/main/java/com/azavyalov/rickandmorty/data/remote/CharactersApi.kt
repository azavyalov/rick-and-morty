package com.azavyalov.rickandmorty.data.remote

import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.data.entities.episode.EpisodeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("/api/character/")
    fun getCharacters(@Query("page") page: String): Single<CharactersResponse>

    @GET("/api/character/{id}")
    fun getCharacterDetails(@Path("id") id: Int): Single<Character>

    @GET("/api/episode/{episodes}")
    fun getMultipleEpisodes(@Path("episodes") episodes: String): Single<EpisodeResponse>
}
