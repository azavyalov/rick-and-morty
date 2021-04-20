package com.azavyalov.rickandmorty.ui.details

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azavyalov.rickandmorty.databinding.ItemEpisodeBinding

class CharacterEpisodesAdapter :
    RecyclerView.Adapter<CharacterEpisodesAdapter.EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class EpisodeViewHolder(private val itemEpisodeBinding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(itemEpisodeBinding.root) {

        fun bind() {

        }
    }
}