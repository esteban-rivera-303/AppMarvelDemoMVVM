package com.estebanrivera.samplemovies.view.details

import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.estebanrivera.samplemovies.R
import com.estebanrivera.samplemovies.data.remote.ResultWrapper
import com.estebanrivera.samplemovies.domain.CharacterDetails
import com.estebanrivera.samplemovies.usecases.GetACharacterUseCases
import com.estebanrivera.samplemovies.usecases.GetFavoriteCharacterStatusUseCase
import com.estebanrivera.samplemovies.usecases.UpdateFavoriteCharacterStatusUseCase
import com.estebanrivera.samplemovies.view.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    var context: Context,
    private val getACharacterUseCases: GetACharacterUseCases,
    private val getFavoriteCharacterStatusUseCase: GetFavoriteCharacterStatusUseCase,
    private val updateFavoriteCharacterStatusUseCase: UpdateFavoriteCharacterStatusUseCase
) : ViewModel() {


    private val disposable = CompositeDisposable()
    private val _detailState = MutableLiveData<DetailState>()
    val detailState: LiveData<DetailState> = _detailState

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val characterDetails = MutableLiveData<CharacterDetails>()

    val colorDominant = MutableLiveData<Int>()

    var job: Job? = null

    fun getCharacterDetail(id: String) {
        _detailState.value = DetailState.Loading
        viewModelScope.launch {
            when (val response = getACharacterUseCases.invoke(id)) {
                is ResultWrapper.NetworkError -> onError(context.getString(R.string.error_network))
                is ResultWrapper.GenericError -> onError(context.getString(R.string.error_generic))
                is ResultWrapper.Success -> {
                    withContext(Dispatchers.Main) {
                        response.let {
                            _detailState.value = DetailState.OnSuccess(response.value)
                            characterDetails.value = response.value
                        }
                    }
                }
            }
        }
    }

    fun getColorDominant(context: Context, url: String) {

        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val image = BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
                Palette.Builder(image).generate {
                    it?.let { palette ->
                        val dominantColor = palette.getDominantColor(
                            ContextCompat.getColor(
                                context,
                                R.color.primary
                            )
                        )
                        colorDominant.postValue(dominantColor)
                    }
                }
            } catch (e: IOException) {
                println(e)
            }
        }
    }

    fun onUpdateFavoriteCharacterStatus() {
        /*characterDetails.value.let {
            disposable.add(
                updateFavoriteCharacterStatusUseCase
                    .invoke(it!!)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe { isFavorite ->
                        _isFavorite.value = isFavorite
                    }
            )
        } */
        characterDetails.value.let {
            viewModelScope.launch {
                _isFavorite.value = updateFavoriteCharacterStatusUseCase.invoke(it!!)
            }
        }
    }

    fun onCharacterValidation(characterId: Int) {
        validateFavoriteCharacterStatus(characterId)
    }

    // Private methods
    private fun validateFavoriteCharacterStatus(characterId: Int) {
        /*disposable.add(
            getFavoriteCharacterStatusUseCase
                .invoke(characterId)
                .subscribe { isFavorite ->
                    _isFavorite.value = isFavorite
                }
        )*/

        viewModelScope.launch {
            //val isFavorite =
            _isFavorite.value = getFavoriteCharacterStatusUseCase.invoke(characterId)
        }

    }

    private fun onError(message: String) {
        _detailState.value = DetailState.OnError(message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        disposable.clear()
    }
}

sealed class DetailState {
    object Loading : DetailState()
    data class OnSuccess(val data: CharacterDetails) : DetailState()
    data class OnError(val message: String) : DetailState()
}