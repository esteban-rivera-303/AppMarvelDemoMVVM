package com.estebanrivera.samplemovies.view.details


import android.os.Bundle
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

        character = intent.getParcelableExtra(BUNDLE_CHARACTER)!!

        binding.name.text = character.name
        binding.characterFavorite.setOnClickListener { viewModel.onUpdateFavoriteCharacterStatus() }

        character.let {
            viewModel.getCharacterDetail(character.id.toString())
        }

        viewModel.detailState.observe(this, Observer { state ->
            when (state) {
                DetailState.Loading -> showLoading()
                is DetailState.OnError -> showError(state.message)
                is DetailState.OnSuccess -> loadData(state.data)
            }
        })

        viewModel.isFavorite.observe(this, Observer(this::updateFavoriteIcon))

        viewModel.colorDominant.observe(this) {
            binding.background.setBackgroundColor(it)
            binding.toolbar.setBackgroundColor(it)
        }

        viewModel.onCharacterValidation(character.id)

    }

    private fun updateFavoriteIcon(isFavorite: Boolean?) {
        binding.characterFavorite.setImageResource(
            if (isFavorite != null && isFavorite) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_favorite_border
            }
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    private fun loadData(character: CharacterDetails) {
        initComponents()
        hideLoading()
        binding.description.text =
            character.description.ifEmpty { getString(R.string.description_placeholder) }
        if (character.thumbNail.isNotEmpty()) {
            Glide.with(this).load(character.thumbNail)
                .into(binding.image)
            viewModel.getColorDominant(character.id, character.thumbNail)
        }

        binding.series.text = getString(R.string.series, character.series?.items?.size)
        binding.comics.text = getString(R.string.comics, character.comics?.items?.size)
        binding.events.text = getString(R.string.events, character.events?.items?.size)
    }

    private fun initComponents() {
        binding.image.visibility = View.VISIBLE
        binding.backImage.visibility = View.VISIBLE
        binding.cardView.visibility = View.VISIBLE
        binding.wrapperError.visibility = View.GONE
    }

    private fun showError(message: String) {
        hideLoading()
        binding.errorText.text = message
        binding.image.visibility = View.GONE
        binding.backImage.visibility = View.GONE
        binding.cardView.visibility = View.GONE
        binding.wrapperError.visibility = View.VISIBLE
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