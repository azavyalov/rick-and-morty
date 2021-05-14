package com.azavyalov.rickandmorty.ui.details.adapter

import com.azavyalov.data.models.Episode
import com.azavyalov.rickandmorty.mapper.AbstractListMapper

/** Маппер информации об эпизоде в модель адаптера */
object EpisodeListAdapterItemMapper : AbstractListMapper<Episode, EpisodeListAdapterItem>() {

    override fun map(from: Episode): EpisodeListAdapterItem {
        return EpisodeListAdapterItem(
            id = from.id,
            name = from.name,
            airDate = from.airDate,
            episode = from.episode,
            characters = from.characters,
            url = from.url,
            created = from.created
        )
    }
}
