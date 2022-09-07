package com.estebanrivera.samplemovies.data.local

import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import com.estebanrivera.samplemovies.domain.ColorCharacter

interface CharacterDataSourceLocal {
    suspend fun getFavoriteCharacterStatus(characterId: Int): Boolean
    suspend fun updateFavoriteCharacterStatus(character: Character): Boolean
    suspend fun updateFavoriteCharacterStatus(character: CharacterDetails): Boolean
    // Color
    suspend fun insertColorCharacter(character: ColorCharacter): Boolean
    suspend fun getColorCharacter(characterId: Int): ColorCharacterEntity?
}