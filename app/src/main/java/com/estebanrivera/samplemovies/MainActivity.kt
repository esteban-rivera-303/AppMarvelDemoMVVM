package com.estebanrivera.samplemovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.estebanrivera.samplemovies.data.CharacterRepository
import com.estebanrivera.samplemovies.data.remote.CharacterDataSourceRemote
import com.estebanrivera.samplemovies.data.remote.CharacterService
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.usescases.GetAllCharactersUseCase
import com.estebanrivera.samplemovies.view.MainContract
import com.estebanrivera.samplemovies.view.MainViewModel

class MainActivity : AppCompatActivity(), MainContract.View {

    private val TAG = "MainActivity"
    private var characterService = CharacterService.getInstance()
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val characterDataSource = CharacterDataSourceRemote(characterService)
        val characterRepository = CharacterRepository(characterDataSource)
        val getAllCharactersUseCase = GetAllCharactersUseCase(characterRepository)
        viewModel = MainViewModel(getAllCharactersUseCase)
        viewModel.characterList.observe(this, Observer {
            Log.e(TAG, "onCreate: $it")
            Log.e("cargó", "YES")
        })
        viewModel.errorMessage.observe(this, Observer {
            Log.e("Error", "error QLO")
        })
        viewModel.getAllCharacter()

    }

    override fun showAllCharacter(data: List<Character>) {
        Log.e("cargó", "YES")
    }

    override fun error() {
        Log.e("Error", "error QLO")
    }

    override fun showLoading() {
        Log.e("Cargando", "...")
    }

    override fun hideLoading() {
        Log.e("No Cargando", "...")
    }
}