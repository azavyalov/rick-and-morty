package com.azavyalov.rickandmorty.ui.characters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.databinding.ItemCharacterBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class CharactersAdapter(private val listener: CharactersItemListener): RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private val items = ArrayList<Character>()

    fun setItems(items: List<Character>) {
        this.items.clear()
        this.items.addAll(items)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    class CharacterViewHolder(
        private val itemBinding: ItemCharacterBinding,
        private val listener: CharactersItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        private lateinit var character: Character

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: Character) {
            character = item
            itemBinding.name.text = item.name
            itemBinding.species.text = item.species
            Glide.with(itemBinding.root)
                .load(item.image)
                .transform(CircleCrop())
                .into(itemBinding.image)
        }
        override fun onClick(v: View?) {
            listener.onCharacterClicked(character.id)
        }
    }
}