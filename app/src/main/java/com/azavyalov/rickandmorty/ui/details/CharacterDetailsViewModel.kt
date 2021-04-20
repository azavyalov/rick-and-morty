package com.azavyalov.rickandmorty.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.data.repository.CharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharacterDetailsViewModel : ViewModel() {

    private val repository = CharactersRepository()
    private val disposable = CompositeDisposable()
    val characterDetails = MutableLiveData<Character>()
    val characterError = MutableLiveData<Boolean>()
    val characterProgress = MutableLiveData<Boolean>()

    fun getCharacterDetails(id: Int) {
        characterProgress.value = true

        disposable.add(
            repository.getCharacterDetails(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Character>() {
                    override fun onSuccess(t: Character) {
                        characterProgress.value = false
                        characterDetails.value = t
                        characterError.value = false
                    }

                    override fun onError(e: Throwable) {
                        characterError.value = true
                    }
                })
        )
    }
}