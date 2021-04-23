package com.azavyalov.network.repository

import com.azavyalov.network.models.Episode
import io.reactivex.Single

interface IEpisodesRepository {

    fun getEpisodesOfCharacter(episodeQuery: String): Single<ArrayList<Episode>>

    fun getEpisodeOfCharacter(episodeQuery: String): Single<Episode>
}
