package com.azavyalov.data.api

import com.azavyalov.data.models.CharacterDetails
import com.azavyalov.data.models.Characters
import com.azavyalov.data.models.Episode
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("/api/character/")
    fun getCharacters(@Query("page") page: String): Single<Characters>

    @GET("/api/character/{id}")
    fun getCharacterDetails(@Path("id") id: Int): Single<CharacterDetails>

    @GET("/api/episode/{episodes}")
    fun getMultipleEpisodes(@Path("episodes") episodes: String): Single<ArrayList<Episode>>

    @GET("/api/episode/{episode}")
    fun getSingleEpisode(@Path("episode") episode: String): Single<Episode>
}
