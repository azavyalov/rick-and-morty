package com.azavyalov.rickandmorty.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azavyalov.rickandmorty.data.entities.Character
import com.azavyalov.rickandmorty.data.entities.Episode
import com.azavyalov.rickandmorty.data.repository.CharactersRepository
import com.azavyalov.rickandmorty.data.repository.EpisodesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharacterDetailsViewModel : ViewModel() {

    private val charactersRepository = CharactersRepository()
    private val episodesRepository = EpisodesRepository()
    private val disposable = CompositeDisposable()
    val details = MutableLiveData<Character>()
    val episodes = MutableLiveData<ArrayList<Episode>>()
    val error = MutableLiveData<Boolean>()
    val progress = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun getCharacterDetails(id: Int) {

        disposable.add(
            charactersRepository.getCharacterDetails(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.value = true
                }
                .doFinally {
                    progress.value = false
                }
                .subscribeWith(object : DisposableSingleObserver<Character>() {
                    override fun onSuccess(t: Character) {
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

        if (isMultipleEpisodes(episodeQuery)) {
            disposable.add(
                episodesRepository.getEpisodesOfCharacter(episodeQuery)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        progress.value = true
                    }
                    .doFinally {
                        progress.value = false
                    }
                    .subscribeWith(object : DisposableSingleObserver<ArrayList<Episode>>() {
                        override fun onSuccess(t: ArrayList<Episode>) {
                            episodes.value = t
                            error.value = false
                        }

                        override fun onError(e: Throwable) {
                            error.value = true
                        }
                    })
            )
        } else {
            disposable.add(
                episodesRepository.getEpisodeOfCharacter(episodeQuery)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        progress.value = true
                    }
                    .doFinally {
                        progress.value = false
                    }
                    .subscribeWith(object : DisposableSingleObserver<Episode>() {
                        override fun onSuccess(t: Episode) {
                            val episodeList = arrayListOf(t)
                            episodes.value = episodeList
                            error.value = false
                        }

                        override fun onError(e: Throwable) {
                            error.value = true
                        }
                    })
            )
        }
    }

    private fun isMultipleEpisodes(value: String): Boolean {
        return value.contains(",")
    }
}
