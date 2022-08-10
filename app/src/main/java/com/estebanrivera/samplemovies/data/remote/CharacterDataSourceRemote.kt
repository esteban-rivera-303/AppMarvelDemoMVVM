package com.estebanrivera.samplemovies.data.remote

import android.util.Log
import com.estebanrivera.samplemovies.data.CharacterDataSource
import com.estebanrivera.samplemovies.data.remote.APIConstants.PRIVATE_KEY
import com.estebanrivera.samplemovies.data.remote.APIConstants.PUBLIC_KEY
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.util.getTimestamp
import com.estebanrivera.samplemovies.util.toHash
import io.reactivex.Single

import retrofit2.Response

class CharacterDataSourceRemote constructor(private val characterService: CharacterService) :
    CharacterDataSource {


    override suspend fun getAllCharacter(): List<Character> {
        val ts = getTimestamp()
        val hash = toHash(ts, PUBLIC_KEY, PRIVATE_KEY)
        return characterService.getAllCharacters(ts, PUBLIC_KEY, hash, 20).body()!!.data!!.results!!.map { characterServer -> characterServer.toCharacterDomain() }
            /*.map {
                Log.e("response HTML: ", it.attributionHTML)
                Log.e("response text: ", it.attributionText)
                Log.e("response count: ", "$it.data.count")
                Log.e("response size: ", "$it.data.results.results.size")
                it.data.results.map { characterServer -> characterServer.toCharacterDomain() }
            }*/


    }

    override suspend fun getCharacterById(id: String): Response<Character> {
        TODO("Not yet implemented")
    }
}