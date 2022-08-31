package com.estebanrivera.samplemovies.data

import com.estebanrivera.samplemovies.data.local.CharacterDataSourceLocal
import com.estebanrivera.samplemovies.data.remote.CharacterDataSourceRemote
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import javax.inject.Inject


class CharacterRepository @Inject constructor(
    private val remoteDataSource: CharacterDataSourceRemote,
    private val localDataSource: CharacterDataSourceLocal
) {
    suspend fun getAllCharacter(limit: Int, offset: Int) =
        remoteDataSource.getAllCharacter(limit, offset)

    suspend fun getACharacterById(id: String) = remoteDataSource.getCharacterById(id)

    suspend fun getFavoriteCharacterStatus(characterId: Int): Boolean =
        localDataSource.getFavoriteCharacterStatus(characterId)

    suspend fun updateFavoriteCharacterStatus(character: Character): Boolean =
        localDataSource.updateFavoriteCharacterStatus(character)

    suspend fun updateFavoriteCharacterStatus(character: CharacterDetails): Boolean =
        localDataSource.updateFavoriteCharacterStatus(character)
}