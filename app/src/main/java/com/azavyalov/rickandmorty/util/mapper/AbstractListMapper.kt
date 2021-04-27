package com.azavyalov.rickandmorty.util.mapper

abstract class AbstractListMapper<FROM, TO> : ListMapper<FROM, TO> {

    override fun map(from: List<FROM>): List<TO> {
        return from.map(::map)
    }
}
