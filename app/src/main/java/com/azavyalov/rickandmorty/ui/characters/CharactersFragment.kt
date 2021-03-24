package com.azavyalov.rickandmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.azavyalov.rickandmorty.databinding.FragmentCharactersBinding

class CharactersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCharactersBinding = FragmentCharactersBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }
}