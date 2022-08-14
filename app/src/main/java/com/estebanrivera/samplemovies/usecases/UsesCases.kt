package com.estebanrivera.samplemovies.usecases

import com.estebanrivera.samplemovies.data.CharacterRepository
import com.estebanrivera.samplemovies.data.remote.ResultWrapper
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import retrofit2.Response
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun invoke(limit: Int, offset: Int): ResultWrapper<List<Character>> =
        characterRepository.getAllCharacter(limit, offset)
}

class GetACharacterUseCases @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun invoke(id: String): ResultWrapper<CharacterDetails> = characterRepository.getACharacterById(id)
}