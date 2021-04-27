package com.azavyalov.rickandmorty.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

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