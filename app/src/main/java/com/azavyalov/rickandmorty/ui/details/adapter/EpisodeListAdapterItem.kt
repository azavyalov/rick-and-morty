package com.azavyalov.rickandmorty.ui.details.adapter

import com.azavyalov.rickandmorty.adapter.DelegateAdapterItem

/** Модель элемента делегат адаптера списка эпизодов */
data class EpisodeListAdapterItem(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) : DelegateAdapterItem {

    override fun id() = id

    override fun content() = hashCode()
}
