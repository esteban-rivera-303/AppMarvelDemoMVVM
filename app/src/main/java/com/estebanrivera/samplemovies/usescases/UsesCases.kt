package com.estebanrivera.samplemovies.usescases

import com.estebanrivera.samplemovies.data.CharacterRepository
import com.estebanrivera.samplemovies.domain.Character
import io.reactivex.Single

class GetAllCharactersUseCase(
    private val characterRepository: CharacterRepository
) {

    suspend fun invoke(): List<Character> =
        characterRepository.getAllCharacter()
}