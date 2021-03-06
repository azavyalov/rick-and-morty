package com.azavyalov.rickandmorty.ui.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.azavyalov.rickandmorty.adapter.DelegateAdapterItem
import com.azavyalov.rickandmorty.databinding.ItemCharacterBinding
import com.azavyalov.rickandmorty.ui.characters.CharactersFragmentDirections
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

/** Адаптер делегат списка персонажей */
class CharacterAdapterDelegate :
    AbsListItemAdapterDelegate<CharacterListAdapterItem, DelegateAdapterItem, CharacterAdapterDelegate.CharacterViewHolder>() {

    override fun isForViewType(item: DelegateAdapterItem, items: MutableList<DelegateAdapterItem>, position: Int): Boolean =
        item is CharacterListAdapterItem

    override fun onCreateViewHolder(parent: ViewGroup): CharacterViewHolder {
        val binding: ItemCharacterBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(item: CharacterListAdapterItem, holder: CharacterViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    /** ViewHolder элемента из списка персонажей */
    inner class CharacterViewHolder(private val itemBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        /** Биндит элемент на его разметку */
        fun bind(item: CharacterListAdapterItem) {
            with(itemBinding) {
                itemName.text = item.name
                itemSpecies.text = item.species
                Glide.with(itemBinding.root)
                    .load(item.image)
                    .transform(CircleCrop())
                    .into(itemImage)
                root.setOnClickListener {
                    val characterId = item.id
                    val action =
                        CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(
                            characterId
                        )
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }
}