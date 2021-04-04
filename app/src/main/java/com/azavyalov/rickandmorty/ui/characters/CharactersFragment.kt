package com.azavyalov.rickandmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azavyalov.rickandmorty.R
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.fragment_characters.progressBar

class CharactersFragment : Fragment() {

    private lateinit var viewModel: CharactersViewModel
    private lateinit var adapter: CharactersAdapter
    private var isAvailableToSearch: Boolean = false

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
                    if (isAvailableToSearch) {
                        viewModel.searchNextPage()
                    }
                }
            }
        })
    }

    private fun setupObservers() {
        observeCharacters()
        observeNextPage()
        observeProgress()
        observeError()
    }

    private fun observeCharacters() {
        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            characters?.let {
                adapter.updateCharacters(it)
            }
        })
    }

    private fun observeNextPage() {
        viewModel.isNextPageAvailable.observe(viewLifecycleOwner, Observer {
            it.let {
                isAvailableToSearch = it
            }
        })
    }

    private fun observeProgress() {
        viewModel.characterProgress.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun observeError() {
        viewModel.characterError.observe(viewLifecycleOwner, Observer {
            it.let {
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