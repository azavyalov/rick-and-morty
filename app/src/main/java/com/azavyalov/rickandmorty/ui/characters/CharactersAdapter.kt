package com.azavyalov.rickandmorty.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.azavyalov.rickandmorty.R
import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.ext.loadFromUrl
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter(private val characters: ArrayList<Character>) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    fun updateCharacters(items: List<Character>) {
        this.characters.clear()
        this.characters.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(inflater.inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.view.detailsImage.loadFromUrl(characters[position].image)
        holder.view.detailsName.text = characters[position].name
        holder.view.detailsSpecies.text = characters[position].name

        holder.view.setOnClickListener {
            val characterId = characters[position].id
            val action = CharactersFragmentDirections
                .actionCharactersFragmentToCharacterDetailsFragment(characterId)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int = characters.size

    class CharacterViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}