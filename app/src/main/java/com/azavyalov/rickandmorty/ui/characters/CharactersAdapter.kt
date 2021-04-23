package com.azavyalov.rickandmorty.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.azavyalov.network.models.Character
import com.azavyalov.rickandmorty.databinding.ItemCharacterBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private val characters: ArrayList<Character> = arrayListOf()

    fun updateCharacters(items: List<Character>) {
        this.characters.clear()
        this.characters.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemCharacterBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    class CharacterViewHolder(private val itemBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Character) {
            with(itemBinding) {
                itemName.text = item.name
                itemSpecies.text = item.species
                Glide.with(itemBinding.root)
                    .load(item.image)
                    .transform(CircleCrop())
                    .into(itemImage)
                root.setOnClickListener {
                    val characterId = item.id
                    val action = CharactersFragmentDirections
                        .actionCharactersFragmentToCharacterDetailsFragment(characterId)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }
}
