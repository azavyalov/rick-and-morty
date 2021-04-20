package com.azavyalov.rickandmorty.data.remote

import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.data.entities.Info

data class CharactersResponse(
    val results: List<Character>,
    val info: Info
)
