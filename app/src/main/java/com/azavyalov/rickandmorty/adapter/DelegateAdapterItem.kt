package com.azavyalov.rickandmorty.adapter

/** Интерфейс элемента делегат-адаптера для отображения в списке RecyclerView */
interface DelegateAdapterItem {

    /** Идентификатор */
    fun id(): Any

    /** Контент для определения необходимости обновления изменений 2-х одинаковых элементов */
    fun content(): Any
}
