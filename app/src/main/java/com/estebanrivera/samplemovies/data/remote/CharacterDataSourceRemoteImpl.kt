package com.estebanrivera.samplemovies.data.remote

import com.estebanrivera.samplemovies.data.remote.APIConstants.PRIVATE_KEY
import com.estebanrivera.samplemovies.data.remote.APIConstants.PUBLIC_KEY
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import com.estebanrivera.samplemovies.util.getTimestamp
import com.estebanrivera.samplemovies.util.toHash
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CharacterDataSourceRemoteImpl @Inject constructor(
    private val characterService: CharacterService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    CharacterDataSourceRemote {

    override suspend fun getAllCharacter(limit: Int, offset: Int): ResultWrapper<List<Character>> {
        val ts = getTimestamp()
        val hash = toHash(ts, PUBLIC_KEY, PRIVATE_KEY)

        return safeApiCall(dispatcher) {
            characterService.getAllCharacters(ts, PUBLIC_KEY, hash, limit, offset)
                .body()!!.data.results.map { characterServer -> characterServer.toCharacterDomain() }
        }
    }

    override suspend fun getCharacterById(id: String): ResultWrapper<CharacterDetails> {
        val ts = getTimestamp()
        val hash = toHash(ts, PUBLIC_KEY, PRIVATE_KEY)
        return safeApiCall(dispatcher) {
            characterService.getCharacterById(id, ts, PUBLIC_KEY, hash)
                .body()!!.data.results.first().toCharacterDetailsDomain()
        }
    }

    // TODO 1
    /*suspend fun getCharacterByIdDeferred(id: String): ResultWrapper<CharacterDetails>{
        val ts = getTimestamp()
        val hash = toHash(ts, PUBLIC_KEY, PRIVATE_KEY)
        val response = characterService.getCharacterByIdDeferred(id, ts, PUBLIC_KEY, hash).await()
        return if(response.isSuccessful)
            ResultWrapper.Success(response.body()!!.data.results.first().toCharacterDetailsDomain())
        else
            ResultWrapper.GenericError(null, null)
    }*/

}