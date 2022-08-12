package com.estebanrivera.samplemovies.view.details

import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import com.estebanrivera.samplemovies.R
import com.estebanrivera.samplemovies.domain.CharacterDetails
import com.estebanrivera.samplemovies.usecases.GetACharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    var getACharacterUseCases: GetACharacterUseCases
) : ViewModel() {

    val character = MutableLiveData<CharacterDetails>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val colorDominant = MutableLiveData<Int>()

    var job: Job? = null

    fun getCharacterDetail(id: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = getACharacterUseCases.invoke(id)
            withContext(Dispatchers.Main) {
                response.let {
                    character.postValue(response)

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
                        loading.value = false
                    }
                }
            } catch (e: IOException) {
                System.out.println(e)
                loading.value = false
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