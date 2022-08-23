package com.estebanrivera.samplemovies.view

import android.content.Context
import androidx.lifecycle.LifecycleObserver
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanrivera.samplemovies.R
import com.estebanrivera.samplemovies.data.remote.ResultWrapper
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.usecases.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val context: Context,
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel(), LifecycleObserver {

    private val _mainState = MutableLiveData<MainState>()
    val mainState: LiveData<MainState> = _mainState

    fun getAllCharacters(limit: Int, offset: Int) {
        _mainState.value = MainState.Loading
        viewModelScope.launch {
            when (val response = getAllCharactersUseCase.invoke(limit, offset)) {
                is ResultWrapper.NetworkError -> onError(context.getString(R.string.error_network))
                is ResultWrapper.GenericError -> onError(context.getString(R.string.error_generic))
                is ResultWrapper.Success -> {
                    response.value.let {
                        _mainState.value = MainState.OnSuccess(it)
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        _mainState.value = MainState.OnError(message)
    }
}

sealed class MainState {
    object Loading : MainState()
    data class OnSuccess(val data: List<Character>) : MainState()
    data class OnError(val message: String) : MainState()
}