package com.azavyalov.data.repository

import com.azavyalov.data.api.CharactersApi
import com.azavyalov.data.di.DaggerApiComponent
import com.azavyalov.data.models.Episode
import io.reactivex.Single
import javax.inject.Inject

class EpisodesRepository : IEpisodesRepository {

    @Inject
    lateinit var api: CharactersApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    override fun getEpisodesOfCharacter(episodeQuery: String): Single<ArrayList<Episode>> {
        return api.getMultipleEpisodes(episodeQuery)
    }

    override fun getEpisodeOfCharacter(episodeQuery: String): Single<Episode> {
        return api.getSingleEpisode(episodeQuery)
    }
}
