package com.estebanrivera.samplemovies.view

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estebanrivera.samplemovies.R
import com.estebanrivera.samplemovies.data.remote.ResultWrapper
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.usecases.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var context: Context,
    var getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    val characterList = MutableLiveData<List<Character>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()


    var job: Job? = null

    fun getAllCharacters(limit: Int, offset: Int) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO).launch {
            when (val response = getAllCharactersUseCase.invoke(limit, offset)) {
                is ResultWrapper.NetworkError -> onError(context.getString(R.string.error_network))
                is ResultWrapper.GenericError -> onError(context.getString(R.string.error_generic))
                is ResultWrapper.Success -> {
                    withContext(Dispatchers.Main) {
                        response.let {
                            characterList.postValue(response.value)
                            loading.value = false
                        }
                    }
                }
            }

        }
    }

    private suspend fun onError(message: String) {
        withContext(Dispatchers.Main) {
            errorMessage.value = message
            loading.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}