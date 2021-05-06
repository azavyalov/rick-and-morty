package com.azavyalov.rickandmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azavyalov.rickandmorty.R
import com.azavyalov.rickandmorty.adapter.DiffUtilDelegatesAdapter
import com.azavyalov.rickandmorty.ui.characters.adapter.CharacterAdapterDelegate
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.fragment_characters.charactersProgressBar
import javax.inject.Inject

class CharactersFragment : Fragment() {

    @Inject lateinit var viewModel: CharactersViewModel
    private var isAvailableToSearch: Boolean = false

    private val adapter: DiffUtilDelegatesAdapter by lazy {
        DiffUtilDelegatesAdapter.Builder()
            .add(CharacterAdapterDelegate())
            .build()
    }

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
        setupProgressObservers()
        setupObservers()
    }

    private fun setupRecycler() {
        val layoutManager = LinearLayoutManager(context)
        charactersRecycler.layoutManager = layoutManager
        charactersRecycler.adapter = adapter
        charactersRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!charactersRecycler.canScrollVertically(1)) {
                    if (isAvailableToSearch) {
                        viewModel.searchNextPage()
                    }
                }
            }
        })
    }

    private fun setupProgressObservers() {
        observeCharactersProgress()
        observePagingProgress()
    }

    private fun setupObservers() {
        observeCharacters()
        observeNextPage()
        observeError()
    }

    private fun observeCharactersProgress() {
        viewModel.charactersProgress.observe(viewLifecycleOwner, {
            it.let {
                if (it) {
                    charactersProgressBar.visibility = View.VISIBLE
                } else {
                    charactersProgressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun observePagingProgress() {
        viewModel.pagingProgress.observe(viewLifecycleOwner, {
            it.let {
                if (it) {
                    pagingProgressBar.visibility = View.VISIBLE
                } else {
                    pagingProgressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun observeCharacters() {
        viewModel.characters.observe(viewLifecycleOwner, { characters ->
            characters?.let {
                adapter.items = it
            }
        })
    }

    private fun observeNextPage() {
        viewModel.isNextPageAvailable.observe(viewLifecycleOwner, {
            it.let {
                isAvailableToSearch = it
            }
        })
    }

    private fun observeError() {
        viewModel.error.observe(viewLifecycleOwner, {
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
