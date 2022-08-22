package com.estebanrivera.samplemovies.view

import android.content.Context
import androidx.lifecycle.LifecycleObserver
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

    private val mainStates: MutableLiveData<State> = MutableLiveData()
    val mainStatesLiveData: LiveData<State> get() = mainStates

    fun getAllCharacters(limit: Int, offset: Int) {
        mainStates.value = State.Loading
        viewModelScope.launch {
            when (val response = getAllCharactersUseCase.invoke(limit, offset)) {
                is ResultWrapper.NetworkError -> onError(context.getString(R.string.error_network))
                is ResultWrapper.GenericError -> onError(context.getString(R.string.error_generic))
                is ResultWrapper.Success -> {
                    response.let {
                        mainStates.postValue(State.OnSuccess(response.value))
                    }
                }
            }

        }
    }

    private fun onError(message: String) {
        mainStates.value = State.OnError(message)
    }

    sealed class State {
        object Loading: State()
        class OnSuccess(val list: List<Character>): State()
        class OnError(val message: String): State()
    }
}