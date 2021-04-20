package com.azavyalov.rickandmorty.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azavyalov.rickandmorty.data.entities.episode.Episode
import com.azavyalov.rickandmorty.databinding.ItemEpisodeBinding

class CharacterEpisodesAdapter :
    RecyclerView.Adapter<CharacterEpisodesAdapter.EpisodeViewHolder>() {

    private val episodes: ArrayList<Episode> = arrayListOf()

    fun updateEpisodes(episodes: List<Episode>) {
        this.episodes.clear()
        this.episodes.addAll(episodes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding: ItemEpisodeBinding =
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int = episodes.size

    class EpisodeViewHolder(private val itemBinding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Episode) {
            with(itemBinding) {
                episodeName.text = item.name
            }
        }
    }
}