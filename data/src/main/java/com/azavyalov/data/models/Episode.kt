package com.azavyalov.data.models

import com.google.gson.annotations.SerializedName

/** Модель информации об эпизодах */
data class Episode(
    val id: Int,
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String

)