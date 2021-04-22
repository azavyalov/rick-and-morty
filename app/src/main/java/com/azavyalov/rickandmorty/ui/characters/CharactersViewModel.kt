package com.azavyalov.rickandmorty.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.data.remote.CharactersResponse
import com.azavyalov.rickandmorty.data.repository.CharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharactersViewModel : ViewModel() {

    private val repository = CharactersRepository()
    private val disposable = CompositeDisposable()
    val characters = MutableLiveData<List<Character>>()
    val error = MutableLiveData<Boolean>()
    val charactersProgress = MutableLiveData<Boolean>()
    val pagingProgress = MutableLiveData<Boolean>()
    val isNextPageAvailable = MutableLiveData<Boolean>()

    private var pageNumber: Int = 1

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun getCharacters() {

        disposable.add(repository.getCharacters("1")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                charactersProgress.value = true
            }
            .doFinally {
                charactersProgress.value = false
            }
            .subscribeWith(object : DisposableSingleObserver<CharactersResponse>() {
                override fun onSuccess(response: CharactersResponse) {
                    error.value = false
                    isNextPageAvailable.value = response.info.next != null
                    characters.value = response.results
                }
                override fun onError(e: Throwable) {
                    error.value = true
                }
            })
        )
    }

    fun searchNextPage() {
        pageNumber += 1

        disposable.add(repository.getCharacters(pageNumber.toString())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                pagingProgress.value = true
            }
            .doFinally {
                pagingProgress.value = false
            }
            .subscribeWith(object : DisposableSingleObserver<CharactersResponse>() {
                override fun onSuccess(response: CharactersResponse) {
                    error.value = false
                    if (response.info.next == null) {
                        pageNumber = 1
                    }
                    isNextPageAvailable.value = response.info.next != null
                    characters.value = characters.value.orEmpty() + response.results
                }
                override fun onError(e: Throwable) {
                    error.value = true
                }
            })
        )
    }
}
