package com.estebanrivera.samplemovies.usecases

import com.estebanrivera.samplemovies.data.CharacterRepository
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun invoke(limit: Int, offset: Int): List<Character> =
        characterRepository.getAllCharacter(limit, offset)
}

class GetACharacterUseCases @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun invoke(id: String): CharacterDetails = characterRepository.getACharacterById(id)
}