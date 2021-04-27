package com.azavyalov.rickandmorty.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.azavyalov.data.models.DelegateAdapterItem

class ComparableItemDiffCallback : DiffUtil.ItemCallback<DelegateAdapterItem>() {

    override fun areItemsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean =
        oldItem.id() == newItem.id()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean =
        oldItem.content() == newItem.content()
}