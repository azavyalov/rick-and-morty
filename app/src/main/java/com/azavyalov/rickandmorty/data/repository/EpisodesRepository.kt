package com.azavyalov.rickandmorty.data.repository

import com.azavyalov.rickandmorty.data.entities.episode.Episode
import com.azavyalov.rickandmorty.data.remote.CharactersApi
import com.azavyalov.rickandmorty.di.DaggerApiComponent
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
