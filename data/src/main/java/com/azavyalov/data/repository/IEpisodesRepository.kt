package com.azavyalov.data.repository

import com.azavyalov.data.models.Episode
import io.reactivex.Single

/** Интерфейс репозитория получения данных об эпизодах */
interface IEpisodesRepository {

    /** Получить информацию об эпизодах персонажа
     * @param episodeQuery номера эпизодов
     * @return список эпизодов
     */
    fun getEpisodesOfCharacter(episodeQuery: String): Single<ArrayList<Episode>>

    /** Получить информацию об эпизоде персонажа
     * @param episodeQuery номер эпизода
     * @return эпизод
     */
    fun getEpisodeOfCharacter(episodeQuery: String): Single<Episode>
}
