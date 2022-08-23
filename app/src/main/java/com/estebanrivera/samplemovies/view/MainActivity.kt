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

        viewModel.mainState.observe(this, Observer { state ->
            when (state) {
                is MainState.Loading -> showLoading()
                is MainState.OnError -> showError(state.message)
                is MainState.OnSuccess -> showData(state.data)
            }
        })

        viewModel.getAllCharacters(50, 0)
    }


    private fun showError(message: String) {
        hideLoading()
        binding.errorText.text = message
        binding.wrapperError.visibility = View.VISIBLE
        binding.recyclerview.visibility = View.GONE
    }

    private fun showData(data: List<Character>) {
        hideLoading()
        binding.recyclerview.visibility = View.VISIBLE
        adapter.setCharactersList(data)
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