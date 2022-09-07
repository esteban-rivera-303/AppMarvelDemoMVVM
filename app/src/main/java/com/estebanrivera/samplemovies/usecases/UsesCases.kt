package com.estebanrivera.samplemovies.usecases

import com.estebanrivera.samplemovies.data.CharacterRepository
import com.estebanrivera.samplemovies.data.remote.ResultWrapper
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import com.estebanrivera.samplemovies.domain.ColorCharacter
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

class GetAColorPredominantCharacter @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun invoke(id:Int ,url :String): ColorCharacter = characterRepository.getAColorCharacterById(id,url)
}


class GetFavoriteCharacterStatusUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun invoke(characterId: Int): Boolean =
       characterRepository.getFavoriteCharacterStatus(characterId)
}

class UpdateFavoriteCharacterStatusUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun invoke(character: Character): Boolean =
        characterRepository.updateFavoriteCharacterStatus(character)

    suspend fun invoke(character: CharacterDetails): Boolean =

        characterRepository.updateFavoriteCharacterStatus(character)
}