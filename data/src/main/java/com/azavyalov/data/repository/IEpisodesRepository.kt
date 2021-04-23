package com.azavyalov.data.repository

import com.azavyalov.data.models.Episode
import io.reactivex.Single

interface IEpisodesRepository {

    fun getEpisodesOfCharacter(episodeQuery: String): Single<ArrayList<Episode>>

    fun getEpisodeOfCharacter(episodeQuery: String): Single<Episode>
}
