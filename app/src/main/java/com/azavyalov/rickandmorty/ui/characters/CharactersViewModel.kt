package com.azavyalov.rickandmorty.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azavyalov.data.models.Characters
import com.azavyalov.data.repository.CharactersRepository
import com.azavyalov.rickandmorty.di.DaggerAppComponent
import com.azavyalov.rickandmorty.ui.characters.adapter.CharacterListAdapterItem
import com.azavyalov.rickandmorty.ui.characters.adapter.CharacterListAdapterItemMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/** Вью модель списка персонажей */
class CharactersViewModel : ViewModel() {

    @Inject lateinit var repository: CharactersRepository
    private val disposable = CompositeDisposable()
    val characters = MutableLiveData<List<CharacterListAdapterItem>>()
    val error = MutableLiveData<Boolean>()
    val charactersProgress = MutableLiveData<Boolean>()
    val pagingProgress = MutableLiveData<Boolean>()
    val isNextPageAvailable = MutableLiveData<Boolean>()

    private var pageNumber: Int = 1

    init {
        DaggerAppComponent.create().inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun getCharacters() {
        disposable.add(repository.getCharacters(FIRST_PAGE.toString())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                charactersProgress.value = true
            }
            .doFinally {
                charactersProgress.value = false
            }
            .subscribeWith(object : DisposableSingleObserver<Characters>() {
                override fun onSuccess(response: Characters) {
                    error.value = false
                    isNextPageAvailable.value = response.info.next != null
                    characters.value = response.results.map(CharacterListAdapterItemMapper::map)
                }

                override fun onError(e: Throwable) {
                    error.value = true
                }
            })
        )
    }

    fun searchNextPage() {
        incrementPage()

        disposable.add(repository.getCharacters(pageNumber.toString())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                pagingProgress.value = true
            }
            .doFinally {
                pagingProgress.value = false
            }
            .subscribeWith(object : DisposableSingleObserver<Characters>() {
                override fun onSuccess(response: Characters) {
                    error.value = false
                    if (response.info.next == null) {
                        restorePageNumber()
                    }
                    isNextPageAvailable.value = response.info.next != null
                    characters.value = characters.value.orEmpty() +
                            response.results.map(CharacterListAdapterItemMapper::map)
                }

                override fun onError(e: Throwable) {
                    error.value = true
                }
            })
        )
    }

    private fun incrementPage() {
        pageNumber += 1
    }

    private fun restorePageNumber() {
        pageNumber = FIRST_PAGE
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}
