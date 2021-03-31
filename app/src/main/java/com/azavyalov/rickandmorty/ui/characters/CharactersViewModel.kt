package com.azavyalov.rickandmorty.ui.characters

import androidx.lifecycle.ViewModel
import com.azavyalov.rickandmorty.data.remote.CharactersService
import io.reactivex.disposables.CompositeDisposable

class CharactersViewModel : ViewModel() {

    private val charactersService = CharactersService()
    private val disposable = CompositeDisposable()

    fun getCharacters() {
        // TODO
    }

    fun searchNextPage() {
        // TODO
    }
}