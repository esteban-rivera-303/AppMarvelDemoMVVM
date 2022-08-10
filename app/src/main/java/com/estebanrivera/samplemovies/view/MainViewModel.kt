package com.estebanrivera.samplemovies.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.usescases.GetAllCharactersUseCase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

class MainViewModel constructor(
    var getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    val characterList = MutableLiveData<List<Character>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()


    var job: Job? = null

    fun getAllCharacter() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = getAllCharactersUseCase.invoke()
            //view!!.showLoading()
            withContext(Dispatchers.Main) {
                response.let {
                    characterList.postValue(response)
                    loading.value = false
                }

            }
        }
        /*disposable.add(
            getAllCharactersUseCase
                .invoke()
                .doOnSubscribe { view!!.showLoading() }
                .subscribe({ characterList ->
                    view!!.showAllCharacter(characterList)

                }, { error ->
                    view!!.hideLoading()
                    view!!.error()
                })
        )*/
    }

    /*override fun onAttach(view: MainContract.View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
        this.disposable.clear()
    }*/

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}