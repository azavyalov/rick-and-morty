package com.azavyalov.rickandmorty.util

object FormatUtils {

    /**
     * Преобразует ссылки из списка эпизодов в список номеров
     * Пример: https://rickandmortyapi.com/api/episode/27, https://rickandmortyapi.com/api/episode/28  -> 27, 28
     * @param episodeList список ссылок на эпизоды
     * @return список номеров эпизода
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
