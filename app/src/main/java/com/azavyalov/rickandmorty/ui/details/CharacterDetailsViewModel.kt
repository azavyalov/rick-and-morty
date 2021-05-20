package com.azavyalov.rickandmorty.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azavyalov.data.repository.CharactersRepository
import com.azavyalov.data.repository.EpisodesRepository
import com.azavyalov.rickandmorty.di.DaggerAppComponent
import com.azavyalov.rickandmorty.ui.characters.adapter.CharacterListAdapterItem
import com.azavyalov.rickandmorty.ui.characters.adapter.CharacterListAdapterItemMapper
import com.azavyalov.rickandmorty.ui.details.adapter.EpisodeListAdapterItem
import com.azavyalov.rickandmorty.ui.details.adapter.EpisodeListAdapterItemMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/** Вью модель списка персонажей */
class CharacterDetailsViewModel : ViewModel() {

    @Inject lateinit var charactersRepository: CharactersRepository
    @Inject lateinit var episodesRepository: EpisodesRepository
    private val disposable = CompositeDisposable()
    val details = MutableLiveData<CharacterListAdapterItem>()
    val episodes = MutableLiveData<List<EpisodeListAdapterItem>>()
    val error = MutableLiveData<Boolean>()
    val progress = MutableLiveData<Boolean>()

    init {
        DaggerAppComponent.create().inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    /** Подписка на получение деталей персонажа по [id] */
    fun getCharacterDetails(id: Int) {
        disposable.add(
            charactersRepository.getCharacterDetails(id)
                .map(CharacterListAdapterItemMapper::map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.value = true
                }
                .subscribeWith(object : DisposableSingleObserver<CharacterListAdapterItem>() {
                    override fun onSuccess(t: CharacterListAdapterItem) {
                        details.value = t
                        error.value = false
                    }

                    override fun onError(e: Throwable) {
                        error.value = true
                    }
                })
        )
    }

    /** Подписка на получение эпизодов персонажа по [episodeQuery] */
    fun getEpisodesOfCharacter(episodeQuery: String) {
        if (isMultipleEpisodes(episodeQuery)) {
            disposable.add(
                episodesRepository.getEpisodesOfCharacter(episodeQuery)
                    .map(EpisodeListAdapterItemMapper::map)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        progress.value = true
                    }
                    .doFinally {
                        progress.value = false
                    }
                    .subscribeWith(object : DisposableSingleObserver<List<EpisodeListAdapterItem>>() {
                        override fun onSuccess(t: List<EpisodeListAdapterItem>) {
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
                    .map(EpisodeListAdapterItemMapper::map)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        progress.value = true
                    }
                    .doFinally {
                        progress.value = false
                    }
                    .subscribeWith(object : DisposableSingleObserver<EpisodeListAdapterItem>() {
                        override fun onSuccess(t: EpisodeListAdapterItem) {
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

    /** Возвращает флаг наличия более одного эпизода у персонажа */
    private fun isMultipleEpisodes(value: String): Boolean {
        return value.contains(",")
    }
}
