package com.azavyalov.rickandmorty.util.mapper

interface ListMapper<FROM, TO> : Mapper<FROM, TO> {

    fun map(from: List<FROM>): List<TO>
}
