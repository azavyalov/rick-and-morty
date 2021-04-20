package com.azavyalov.rickandmorty.data.repository

import com.azavyalov.rickandmorty.data.entities.episode.EpisodeResponse
import io.reactivex.Single

interface IEpisodesRepository {

    fun getEpisodesOfCharacter(episodeQuery: String): Single<EpisodeResponse>
}