package com.azavyalov.rickandmorty.data.repository

import com.azavyalov.rickandmorty.data.entities.episode.Episode
import io.reactivex.Single

interface IEpisodesRepository {

    fun getEpisodesOfCharacter(episodeQuery: String): Single<ArrayList<Episode>>

    fun getEpisodeOfCharacter(episodeQuery: String): Single<Episode>
}
