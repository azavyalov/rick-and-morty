package com.azavyalov.rickandmorty.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azavyalov.rickandmorty.adapter.DelegateAdapterItem
import com.azavyalov.rickandmorty.databinding.ItemEpisodeBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

/** Адаптер делегат списка эпизодов */
class EpisodeAdapterDelegate :
    AbsListItemAdapterDelegate<EpisodeListAdapterItem, DelegateAdapterItem, EpisodeAdapterDelegate.EpisodeViewHolder>() {

    override fun isForViewType(item: DelegateAdapterItem, items: MutableList<DelegateAdapterItem>, position: Int): Boolean =
        item is EpisodeListAdapterItem

    override fun onCreateViewHolder(parent: ViewGroup): EpisodeViewHolder {
        val binding: ItemEpisodeBinding =
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(item: EpisodeListAdapterItem, holder: EpisodeViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    /** ViewHolder элемента из списка эпизодов */
    inner class EpisodeViewHolder(private val itemBinding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        /** Биндит элемент на его разметку */
        fun bind(item: EpisodeListAdapterItem) {
            with(itemBinding) {
                episodeName.text = item.name
                episodeNumber.text = item.episode
            }
        }
    }
}