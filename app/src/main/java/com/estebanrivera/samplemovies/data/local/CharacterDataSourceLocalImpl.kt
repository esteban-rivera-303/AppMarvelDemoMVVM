package com.estebanrivera.samplemovies.data.local

import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import com.estebanrivera.samplemovies.domain.ColorCharacter


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
        return characterDao.getCharacterById(characterEntity.id)?.let {
            characterDao.deleteCharacter(characterEntity)
            false
        } ?: run {
            characterDao.insertCharacter(characterEntity)
            true
        }
    }

    override suspend fun updateFavoriteCharacterStatus(character: Character): Boolean {
        val characterEntity = character.toCharacterEntity()
        return characterDao.getCharacterById(characterEntity.id)?.let {
            characterDao.deleteCharacter(characterEntity)
            false
        } ?: run {
            characterDao.insertCharacter(characterEntity)
            true
        }

    }

    override suspend fun insertColorCharacter(character: ColorCharacter): Boolean {
        val characterEntity = character.toColorCharacterEntity()
        return characterDao.getColorCharacterById(characterEntity.id)?.let {
            false
        }?: run {
            characterDao.insertCharacter(characterEntity)
            true
        }

    }

    override suspend fun getColorCharacter(characterId: Int): ColorCharacterEntity? {
        return characterDao.getColorCharacterById(characterId)
    }
}