package com.estebanrivera.samplemovies.data

import com.estebanrivera.samplemovies.domain.Character
import io.reactivex.Single
import retrofit2.Response

interface CharacterDataSource {
    suspend fun getAllCharacter(): List<Character>
    suspend fun getCharacterById(id: String): Response<Character>
}