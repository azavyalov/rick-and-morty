package com.azavyalov.rickandmorty.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azavyalov.data.models.DelegateAdapterItem
import com.azavyalov.data.models.Episode
import com.azavyalov.rickandmorty.databinding.ItemEpisodeBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class EpisodeAdapterDelegate :
    AbsListItemAdapterDelegate<Episode, DelegateAdapterItem, EpisodeAdapterDelegate.EpisodeViewHolder>() {

    override fun isForViewType(item: DelegateAdapterItem, items: MutableList<DelegateAdapterItem>, position: Int): Boolean =
        item is Episode

    override fun onCreateViewHolder(parent: ViewGroup): EpisodeViewHolder {
        val binding: ItemEpisodeBinding =
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(item: Episode, holder: EpisodeViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    inner class EpisodeViewHolder(private val itemBinding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Episode) {
            with(itemBinding) {
                episodeName.text = item.name
                episodeNumber.text = item.episode
            }
        }
    }
}