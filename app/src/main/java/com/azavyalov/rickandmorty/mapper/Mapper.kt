package com.azavyalov.rickandmorty.mapper

/**
 * Маппер сущностей из типа [FROM] в тип [TO]
 * @param FROM тип входных данных
 * @param TO тип результирующих данных
 */
interface Mapper<FROM, TO> {

    fun map(from: FROM): TO
}
