package com.estebanrivera.samplemovies.data


class CharacterRepository(private val dataSource: CharacterDataSource) {
    suspend fun getAllCharacter() = dataSource.getAllCharacter()
}