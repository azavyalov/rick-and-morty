package com.azavyalov.rickandmorty.ui.characters.adapter

import com.azavyalov.rickandmorty.adapter.DelegateAdapterItem

data class CharacterListAdapterItem(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val url: String,
    val image: String,
    val episode: List<String>,
    val created: String
) : DelegateAdapterItem {

    override fun id() = id

    override fun content() = hashCode()
}
