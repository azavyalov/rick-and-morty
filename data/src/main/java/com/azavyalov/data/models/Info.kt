package com.azavyalov.data.models

/** Модель информации о страницах */
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
