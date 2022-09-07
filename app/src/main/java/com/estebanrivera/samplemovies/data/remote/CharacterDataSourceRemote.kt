package com.estebanrivera.samplemovies.data.remote

import com.estebanrivera.samplemovies.data.local.ColorCharacterEntity
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails

interface CharacterDataSourceRemote {
    suspend fun getAllCharacter(limit: Int, offset: Int): ResultWrapper<List<Character>>
    suspend fun getCharacterById(id: String): ResultWrapper<CharacterDetails>

    // Color
    suspend fun getColorPredominantCharacter(id: Int, url: String): ColorCharacterEntity
}