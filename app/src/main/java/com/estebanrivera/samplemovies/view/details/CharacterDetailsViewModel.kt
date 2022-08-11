package com.estebanrivera.samplemovies.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estebanrivera.samplemovies.domain.CharacterDetails
import com.estebanrivera.samplemovies.usecases.GetACharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    var getACharacterUseCases: GetACharacterUseCases
) : ViewModel() {

    val character = MutableLiveData<CharacterDetails>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    var job: Job? = null

    fun getCharacterDetail(id: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = getACharacterUseCases.invoke(id)
            withContext(Dispatchers.Main) {
                response.let {
                    character.postValue(response)
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