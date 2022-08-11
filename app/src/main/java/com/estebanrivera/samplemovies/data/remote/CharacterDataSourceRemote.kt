package com.estebanrivera.samplemovies.data.remote

import android.util.Log
import com.estebanrivera.samplemovies.data.CharacterDataSource
import com.estebanrivera.samplemovies.data.remote.APIConstants.PRIVATE_KEY
import com.estebanrivera.samplemovies.data.remote.APIConstants.PUBLIC_KEY
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import com.estebanrivera.samplemovies.util.getTimestamp
import com.estebanrivera.samplemovies.util.toHash
import javax.inject.Inject

class CharacterDataSourceRemote @Inject constructor(private val characterService: CharacterService) :
    CharacterDataSource {

    override suspend fun getAllCharacter(limit: Int, offset: Int): List<Character> {
        val ts = getTimestamp()
        val hash = toHash(ts, PUBLIC_KEY, PRIVATE_KEY)
        return characterService.getAllCharacters(ts, PUBLIC_KEY, hash, limit, offset)
            .body()!!.data.results.map { characterServer -> characterServer.toCharacterDomain() }
    }

    override suspend fun getCharacterById(id: String): CharacterDetails {
        val ts = getTimestamp()
        val hash = toHash(ts, PUBLIC_KEY, PRIVATE_KEY)
        return characterService.getCharacterById(id, ts, PUBLIC_KEY, hash)
            .body()!!.data.results.first().toCharacterDetailsDomain()
    }
}