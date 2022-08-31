package com.estebanrivera.samplemovies.data.local

import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails


import javax.inject.Inject

class CharacterDataSourceLocalImpl @Inject constructor(
    private var database: CharacterDatabase,
) :
    CharacterDataSourceLocal {

    private val characterDao by lazy { database.characterDao() }

    override suspend fun getFavoriteCharacterStatus(characterId: Int): Boolean {
        val response = characterDao.getCharacterById(characterId)
        return response != null
    }

    override suspend fun updateFavoriteCharacterStatus(character: CharacterDetails): Boolean {
        val characterEntity = character.toCharacterEntity()
        val response = characterDao.getCharacterById(characterEntity.id)
        return if (response == null) {
            characterDao.insertCharacter(characterEntity)
            true
        } else {
            characterDao.deleteCharacter(characterEntity)
            false
        }
    }


    override suspend fun updateFavoriteCharacterStatus(character: Character): Boolean {
        val characterEntity = character.toCharacterEntity()
        val response = characterDao.getCharacterById(characterEntity.id)
        return if (response == null) {
            characterDao.insertCharacter(characterEntity)
            true
        } else {
            characterDao.deleteCharacter(characterEntity)
            false
        }
    }
}