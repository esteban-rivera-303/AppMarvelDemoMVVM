package com.estebanrivera.samplemovies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.estebanrivera.samplemovies.databinding.ItemRowCharacterBinding
import com.estebanrivera.samplemovies.domain.Character

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var chararacters = mutableListOf<Character>()

    fun setCharactersList(characters: List<Character>) {
        this.chararacters = characters.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowCharacterBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val character = chararacters[position]
        holder.binding.name.text = character.name
        if (!character.thumbNail.isNullOrEmpty())
            Glide.with(holder.itemView.context).load(character.thumbNail.replace("http:","https:"))
                .into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return chararacters.size
    }
}

class MainViewHolder(val binding: ItemRowCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
}