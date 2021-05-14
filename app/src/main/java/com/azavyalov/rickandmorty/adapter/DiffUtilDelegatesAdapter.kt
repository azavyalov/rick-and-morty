package com.azavyalov.rickandmorty.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

/**
 * Адаптер RecyclerView, работающий через делегаты элементов списка
 * @param diffCallback коллбэк для подсчета различий между двумя списками
 * @param delegates делегаты элементов списка
 */
open class DiffUtilDelegatesAdapter protected constructor(
    diffCallback: DiffUtil.ItemCallback<DelegateAdapterItem>,
    vararg delegates: AdapterDelegate<List<DelegateAdapterItem>>
) : AsyncListDifferDelegationAdapter<DelegateAdapterItem>(diffCallback) {

    init {
        delegates.forEach {
            delegatesManager.addDelegate(it)
        }
    }

    class Builder {

        private val delegates = HashSet<AdapterDelegate<List<DelegateAdapterItem>>>()

        private var diffCallback: DiffUtil.ItemCallback<DelegateAdapterItem> = ComparableItemDiffCallback()

        fun add(delegate: AdapterDelegate<List<DelegateAdapterItem>>): Builder {
            delegates.add(delegate)
            return this
        }

        fun build(): DiffUtilDelegatesAdapter =
            DiffUtilDelegatesAdapter(diffCallback, *delegates.toTypedArray())
    }
}