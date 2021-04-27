package com.azavyalov.rickandmorty.util.mapper

interface Mapper<FROM, TO> {

    fun map(from: FROM): TO
}
