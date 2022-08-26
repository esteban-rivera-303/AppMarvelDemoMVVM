package com.estebanrivera.samplemovies.data.local

import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import io.reactivex.Maybe

interface CharacterDataSourceLocal {
    fun getFavoriteCharacterStatus(characterId: Int): Maybe<Boolean>
    fun updateFavoriteCharacterStatus(character: Character): Maybe<Boolean>
    fun updateFavoriteCharacterStatus(character: CharacterDetails): Maybe<Boolean>
}