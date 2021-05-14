package com.azavyalov.rickandmorty.ui.characters.adapter

import com.azavyalov.data.models.CharacterDetails
import com.azavyalov.rickandmorty.mapper.AbstractListMapper

/** Маппер информации о деталях персонажа в модель адаптера*/
object CharacterListAdapterItemMapper :
    AbstractListMapper<CharacterDetails, CharacterListAdapterItem>() {

    override fun map(from: CharacterDetails): CharacterListAdapterItem {
        return CharacterListAdapterItem(
            id = from.id,
            name = from.name,
            status = from.status,
            species = from.species,
            type = from.type,
            gender = from.gender,
            url = from.url,
            image = from.image,
            episode = from.episode,
            created = from.created
        )
    }
}
