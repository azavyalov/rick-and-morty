package com.azavyalov.rickandmorty.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.azavyalov.rickandmorty.R
import com.azavyalov.rickandmorty.data.entities.episode.Episode
import com.azavyalov.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.azavyalov.rickandmorty.ui.characters.CharactersAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_character_details.*

class CharacterDetailsFragment : Fragment() {

    private lateinit var viewModel: CharacterDetailsViewModel
    private lateinit var adapter: CharacterEpisodesAdapter
    private lateinit var characterDetailsBinding: FragmentCharacterDetailsBinding
    private var characterId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        characterDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_character_details, container, false
        )
        return characterDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CharacterDetailsViewModel::class.java)
        arguments?.let {
            characterId = CharacterDetailsFragmentArgs.fromBundle(it).characterId
        }

        setupRecycler()
        getCharacter(characterId)
    }

    private fun setupRecycler() {
        val layoutManager = LinearLayoutManager(context)
        episodesRecycler.layoutManager = layoutManager
        adapter = CharacterEpisodesAdapter()
        episodesRecycler.adapter = adapter
    }

    private fun getCharacter(id: Int) {
        viewModel.getCharacterDetails(id)
        viewModel.details.observe(viewLifecycleOwner, Observer { character ->
            if (character != null) {
                characterDetailsBinding.characterDetail = character
                val episodesQuery = parseEpisodes(character.episode)
                getEpisodes(episodesQuery)
            }
        })
    }

    private fun setupObservers() {
        observeCharacterProgress()
        observeCharacterDetails()
        observeCharacterError()
    }

    private fun observeCharacterProgress() {
        viewModel.progress.observe(viewLifecycleOwner, Observer { progress ->
            progress?.let {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun observeCharacterDetails() {
        viewModel.details.observe(viewLifecycleOwner, Observer { character ->
            character?.let {
                characterDetailsBinding.characterDetail = character
            }
        })
    }

    private fun observeCharacterError() {
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    Toast.makeText(
                        context,
                        getString(R.string.error_has_occurred),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun parseEpisodes(episodeList: List<String>): String {
        var episodes = ""
        episodeList.forEachIndexed { index, episode ->
            episodes += episode.split("/").last()
            if (index != episodeList.size - 1) {
                episodes += ","
            }
        }
        return episodes
    }

    private fun getEpisodes(episodeQuery: String) {
        viewModel.getEpisodesOfCharacter(episodeQuery)
        viewModel.episodes.observe(viewLifecycleOwner, Observer { episodes ->
            episodes?.let {
                adapter.updateEpisodes(episodes)
            }
        })
    }
}