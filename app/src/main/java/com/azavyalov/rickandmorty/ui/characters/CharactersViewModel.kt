package com.azavyalov.rickandmorty.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.data.remote.CharactersResponse
import com.azavyalov.rickandmorty.data.remote.CharactersService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharactersViewModel : ViewModel() {

    private val charactersService = CharactersService()
    private val disposable = CompositeDisposable()
    val characters = MutableLiveData<List<Character>>()
    val characterError = MutableLiveData<Boolean>()
    val characterProgress = MutableLiveData<Boolean>()
    val isNextPageAvailable = MutableLiveData<Boolean>()

    private var pageNumber: Int = 1

    fun getCharacters() {
        characterProgress.value = true

        disposable.add(charactersService.getCharacters("1")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<CharactersResponse>() {
                override fun onSuccess(response: CharactersResponse) {
                    characterProgress.value = false
                    isNextPageAvailable.value = response.info.next != null
                    characters.value = response.results
                    characterError.value = false
                }
                override fun onError(e: Throwable) {
                    characterError.value = true
                }
            })
        )
    }

    fun searchNextPage() {
        pageNumber += 1

        disposable.add(charactersService.getCharacters(pageNumber.toString())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<CharactersResponse>() {
                override fun onSuccess(response: CharactersResponse) {
                    isNextPageAvailable.value = response.info.next != null
                    characters.value = characters.value.orEmpty() + response.results
                    characterError.value = false
                }
                override fun onError(e: Throwable) {
                    characterError.value = true
                }
            })
        )
    }
}