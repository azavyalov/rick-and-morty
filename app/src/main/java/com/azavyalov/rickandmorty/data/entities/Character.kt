package com.azavyalov.rickandmorty.data.entities

data class Character(
    // TODO make as primary key
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val url: String,
    val image: String,
    val created: String
)
