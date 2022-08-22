package com.estebanrivera.samplemovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.estebanrivera.samplemovies.R
import com.estebanrivera.samplemovies.databinding.ActivityMainBinding
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.util.Constants
import com.estebanrivera.samplemovies.util.startActivity
import com.estebanrivera.samplemovies.view.details.CharacterDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickCharacter {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val adapter = MainAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.adapter = adapter

        viewModel.mainStatesLiveData.observe(this, Observer {
            when(it) {
                is MainViewModel.State.Loading -> {
                    showLoading()
                }
                is MainViewModel.State.OnSuccess -> {
                    hideLoading()
                    binding.recyclerview.visibility = View.VISIBLE
                    adapter.setCharactersList(it.list)
                }
                is MainViewModel.State.OnError -> {
                    showError(it.message)
                }
            }
        })
        viewModel.getAllCharacters(20, 0)
    }


    private fun showError(message: String) {
        hideLoading()
        binding.errorText.text = message
        binding.wrapperError.visibility = View.VISIBLE
        binding.recyclerview.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progress.visibility = View.GONE
    }

    override fun onClick(character: Character) {
        startActivity<CharacterDetailsActivity> {
            putExtra(Constants.BUNDLE_CHARACTER, character)
        }
        overridePendingTransition(R.anim.entry, R.anim.exit)
    }
}