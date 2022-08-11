package com.estebanrivera.samplemovies.data

import com.estebanrivera.samplemovies.data.remote.CharacterDataSourceRemote
import javax.inject.Inject


class CharacterRepository @Inject constructor(private val dataSource: CharacterDataSourceRemote) {
    suspend fun getAllCharacter(limit: Int, offset: Int) = dataSource.getAllCharacter(limit, offset)
    suspend fun getACharacterById(id: String) = dataSource.getCharacterById(id)
}