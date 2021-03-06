package com.azavyalov.rickandmorty.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/** Обработчик различий между элементами списка делегат-адаптера */
class ComparableItemDiffCallback : DiffUtil.ItemCallback<DelegateAdapterItem>() {

    override fun areItemsTheSame(oldItem: DelegateAdapterItem, newItem: DelegateAdapterItem): Boolean =
        oldItem.id() == newItem.id()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DelegateAdapterItem, newItem: DelegateAdapterItem): Boolean =
        oldItem.content() == newItem.content()
}