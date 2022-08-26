package com.estebanrivera.samplemovies.data

import com.estebanrivera.samplemovies.data.local.CharacterDataSourceLocal
import com.estebanrivera.samplemovies.data.remote.CharacterDataSourceRemote
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import io.reactivex.Maybe
import javax.inject.Inject


class CharacterRepository @Inject constructor(
    private val remoteDataSource: CharacterDataSourceRemote,
    private val localDataSource: CharacterDataSourceLocal
) {
    suspend fun getAllCharacter(limit: Int, offset: Int) =
        remoteDataSource.getAllCharacter(limit, offset)

    suspend fun getACharacterById(id: String) = remoteDataSource.getCharacterById(id)

    fun getFavoriteCharacterStatus(characterId: Int): Maybe<Boolean> =
        localDataSource.getFavoriteCharacterStatus(characterId)

    fun updateFavoriteCharacterStatus(character: Character): Maybe<Boolean> =
        localDataSource.updateFavoriteCharacterStatus(character)

    fun updateFavoriteCharacterStatus(character: CharacterDetails): Maybe<Boolean> =
        localDataSource.updateFavoriteCharacterStatus(character)
}