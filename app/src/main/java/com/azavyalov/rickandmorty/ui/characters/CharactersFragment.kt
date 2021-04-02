package com.azavyalov.rickandmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azavyalov.rickandmorty.R
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : Fragment() {

    private lateinit var viewModel: CharactersViewModel
    private lateinit var adapter: CharactersAdapter
    private var availableToSearch: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CharactersViewModel::class.java)
        viewModel.getCharacters()

        setupRecycler()
        setupObservers()
    }

    private fun setupRecycler() {
        val layoutManager = LinearLayoutManager(context)
        charactersRecycler.layoutManager = layoutManager

        adapter = CharactersAdapter()
        charactersRecycler.adapter = adapter

        charactersRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (charactersRecycler.canScrollVertically(1)) {
                    if (availableToSearch) {
                        viewModel.searchNextPage()
                    }
                }
            }
        })
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            characters?.let {
                characterError.visibility = View.GONE
                adapter.updateCharacters(it)
            }
        })
    }
}