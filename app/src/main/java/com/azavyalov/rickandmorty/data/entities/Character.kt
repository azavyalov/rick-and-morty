package com.azavyalov.rickandmorty.data.entities

data class Character(
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
)
