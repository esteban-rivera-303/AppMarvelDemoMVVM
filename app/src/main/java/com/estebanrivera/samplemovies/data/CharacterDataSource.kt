package com.estebanrivera.samplemovies.data

import com.estebanrivera.samplemovies.data.remote.ResultWrapper
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import io.reactivex.Single
import retrofit2.Response

interface CharacterDataSource {
    suspend fun getAllCharacter(limit: Int, offset: Int): ResultWrapper<List<Character>>
    suspend fun getCharacterById(id: String): ResultWrapper<CharacterDetails>
}