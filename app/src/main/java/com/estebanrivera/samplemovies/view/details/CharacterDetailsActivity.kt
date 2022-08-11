package com.estebanrivera.samplemovies.view.details

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.estebanrivera.samplemovies.databinding.ActivityCharacterDetailsBinding
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.util.Constants.BUNDLE_CHARACTER
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailsBinding
    private val viewModel by viewModels<CharacterDetailsViewModel>()
    private lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        character = intent.getParcelableExtra<Character>(BUNDLE_CHARACTER)!!

        binding.name.text = character.name

        viewModel.character.observe(this) {
            binding.description.text = it.description
        }

        viewModel.errorMessage.observe(this, Observer {
            showError("No se pudo mostrar el contenido")
        })

        viewModel.loading.observe(this) {
            if (it) showLoading() else hideLoading()
        }
        character.let {
            viewModel.getCharacterDetail(character.id.toString())
        }

    }

    private fun showError(message: String) {
        Log.e("Error", message)
    }

    private fun showLoading() {
        Log.e("Cargando", "...")
    }

    private fun hideLoading() {
        Log.e("No Cargando", "...")
    }
}