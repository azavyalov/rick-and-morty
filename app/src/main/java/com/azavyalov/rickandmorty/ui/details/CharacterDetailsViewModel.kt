package com.azavyalov.rickandmorty.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.data.entities.episode.EpisodeResponse
import com.azavyalov.rickandmorty.data.repository.CharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharacterDetailsViewModel : ViewModel() {

    private val repository = CharactersRepository()
    private val disposable = CompositeDisposable()
    val details = MutableLiveData<Character>()
    val error = MutableLiveData<Boolean>()
    val progress = MutableLiveData<Boolean>()
    val episodes = MutableLiveData<EpisodeResponse>()

    fun getCharacterDetails(id: Int) {
        progress.value = true

        disposable.add(
            repository.getCharacterDetails(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Character>() {
                    override fun onSuccess(t: Character) {
                        progress.value = false
                        details.value = t
                        error.value = false
                    }
                    override fun onError(e: Throwable) {
                        error.value = true
                    }
                })
        )
    }

    fun getEpisodesOfCharacter(episodeQuery: String) {

        disposable.add(
            repository.getEpisodesOfCharacter(episodeQuery)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<EpisodeResponse>() {
                    override fun onSuccess(t: EpisodeResponse) {
                        episodes.value = t
                    }
                    override fun onError(e: Throwable) {
                        error.value = true
                    }
                })
        )
    }
}