package com.azavyalov.data.models

/** Модель информации о персонажах */
data class Characters(
    val info: Info,
    val results: List<CharacterDetails>
)
