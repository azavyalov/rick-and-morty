package com.azavyalov.rickandmorty.mapper

/**
 * Абстрактный маппер списка сущностей типа [FROM] в тип [TO]
 * @param FROM тип входных данных
 * @param TO тип результирующих данных
 */
abstract class AbstractListMapper<FROM, TO> : ListMapper<FROM, TO> {

    override fun map(from: List<FROM>): List<TO> {
        return from.map(::map)
    }
}
