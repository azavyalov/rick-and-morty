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
import com.azavyalov.rickandmorty.R
import com.azavyalov.rickandmorty.databinding.FragmentCharacterDetailsBinding
import kotlinx.android.synthetic.main.fragment_character_details.*

class CharacterDetailsFragment : Fragment() {

    private lateinit var viewModel: CharacterDetailsViewModel
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

        arguments?.let {
            characterId = CharacterDetailsFragmentArgs.fromBundle(it).characterId
        }

        viewModel = ViewModelProviders.of(this).get(CharacterDetailsViewModel::class.java)
        viewModel.getCharacterDetails(characterId)

        setupObservers()
    }

    private fun setupObservers() {
        observeCharacterProgress()
        observeCharacterDetails()
        observeCharacterError()
    }

    private fun observeCharacterProgress() {
        viewModel.characterProgress.observe(viewLifecycleOwner, Observer { progress ->
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
        viewModel.characterDetails.observe(viewLifecycleOwner, Observer { character ->
            character?.let {
                characterDetailsBinding.characterDetail = character
            }
        })
    }

    private fun observeCharacterError() {
        viewModel.characterError.observe(viewLifecycleOwner, Observer { error ->
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
}