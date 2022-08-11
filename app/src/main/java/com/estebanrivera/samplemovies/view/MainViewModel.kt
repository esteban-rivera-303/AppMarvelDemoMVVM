package com.estebanrivera.samplemovies.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.usecases.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    val characterList = MutableLiveData<List<Character>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()


    var job: Job? = null

    fun getAllCharacters(limit: Int,offset: Int) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = getAllCharactersUseCase.invoke(limit, offset)
            withContext(Dispatchers.Main) {
                response.let {
                    characterList.postValue(response)
                    loading.value = false
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}