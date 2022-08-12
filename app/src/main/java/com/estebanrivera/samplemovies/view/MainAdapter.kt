package com.estebanrivera.samplemovies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.estebanrivera.samplemovies.databinding.ItemRowCharacterBinding
import com.estebanrivera.samplemovies.domain.Character

class MainAdapter constructor(var onClickCharacter: OnClickCharacter) :
    RecyclerView.Adapter<MainViewHolder>() {
    private var characters = mutableListOf<Character>()

    fun setCharactersList(characters: List<Character>) {
        this.characters = characters.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowCharacterBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val character = characters[position]
        holder.binding.name.text = character.name
        if (character.description.isNotEmpty())
            holder.binding.description.text = character.description
        holder.binding.number.text = character.id.toString()
        if (character.thumbNail.isNotEmpty())
            Glide.with(holder.itemView.context).load(character.thumbNail)
                .into(holder.binding.imageview)

        holder.binding.root.setOnClickListener { onClickCharacter.onClick(character) }
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}

class MainViewHolder(val binding: ItemRowCharacterBinding) : RecyclerView.ViewHolder(binding.root)

interface OnClickCharacter {
    fun onClick(character: Character)
}