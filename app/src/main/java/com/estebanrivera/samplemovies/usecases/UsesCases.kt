package com.estebanrivera.samplemovies.usecases

import com.estebanrivera.samplemovies.data.CharacterRepository
import com.estebanrivera.samplemovies.data.remote.ResultWrapper
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import io.reactivex.Maybe
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


class GetFavoriteCharacterStatusUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    fun invoke(characterId: Int): Maybe<Boolean> =
        characterRepository.getFavoriteCharacterStatus(characterId)
}

class UpdateFavoriteCharacterStatusUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    fun invoke(character: Character): Maybe<Boolean> =
        characterRepository.updateFavoriteCharacterStatus(character)

    fun invoke(character: CharacterDetails): Maybe<Boolean> =
        characterRepository.updateFavoriteCharacterStatus(character)
}