package com.azavyalov.rickandmorty.util

object FormatUtils {

    /**
     * Преобразует список ссылок в строку со списком номеров (эпизодов)
     * Пример: https://rickandmortyapi.com/api/episode/27, https://rickandmortyapi.com/api/episode/28  -> 27, 28
     * @param episodeList список ссылок на эпизоды
     * @return строка со списком эпизодов
     */
    fun parseEpisodes(episodeList: List<String>): String {
        var episodes = ""
        episodeList.forEachIndexed { index, episode ->
            episodes += episode.split("/").last()
            if (index != episodeList.size - 1) {
                episodes += ","
            }
        }
        return episodes
    }
}
