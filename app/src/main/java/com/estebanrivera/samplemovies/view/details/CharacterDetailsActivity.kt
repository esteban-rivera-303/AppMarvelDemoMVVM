package com.estebanrivera.samplemovies.view.details



import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.estebanrivera.samplemovies.R

import com.estebanrivera.samplemovies.databinding.ActivityCharacterDetailsBinding
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
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
        initToolbar()

        character = intent.getParcelableExtra<Character>(BUNDLE_CHARACTER)!!
        binding.name.text = character.name

        viewModel.character.observe(this) {
            loadData(it)
        }

        viewModel.errorMessage.observe(this, Observer {
            showError(getString(R.string.error_generic))
        })

        viewModel.loading.observe(this) {
            if (it) showLoading() else hideLoading()
        }

        viewModel.colorDominant.observe(this) {
            binding.background.setBackgroundColor(it)
            binding.toolbar.setBackgroundColor(it)
        }

        character.let {
            viewModel.getCharacterDetail(character.id.toString())
        }


    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadData(character: CharacterDetails) {

        binding.description.text =
            character.description.ifEmpty { getString(R.string.description_placeholder) }
        if (character.thumbNail.isNotEmpty()) {
            Glide.with(this).load(character.thumbNail)
                .into(binding.image)
            viewModel.getColorDominant(this, character.thumbNail)
        }

        binding.series.text = getString(R.string.series , character.series?.items?.size)
        binding.comics.text = getString(R.string.comics , character.comics?.items?.size)
        binding.events.text = getString(R.string.events , character.events?.items?.size)


    }

    private fun showError(message: String) {
        Log.e("Error", message)
    }

    private fun showLoading() {
        binding.progress.visibility = View.VISIBLE
        binding.wrapperProgress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progress.visibility = View.GONE
        binding.wrapperProgress.visibility = View.GONE
    }

}