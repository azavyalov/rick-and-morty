package com.azavyalov.rickandmorty.mapper

/**
 * Маппер списка сущностей типа [FROM] в тип [TO]
 * @param FROM тип входных данных
 * @param TO тип результирующих данных
 */
interface ListMapper<FROM, TO> : Mapper<FROM, TO> {

    fun map(from: List<FROM>): List<TO>
}
