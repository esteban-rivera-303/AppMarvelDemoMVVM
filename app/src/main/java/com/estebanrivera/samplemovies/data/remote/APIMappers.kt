package com.estebanrivera.samplemovies.data.remote

import com.estebanrivera.samplemovies.domain.Character

fun CharacterServer.toCharacterDomain(): Character {
       return Character(
            id,
            name!!,
            description!!,
        )
}


/*fun CharacterServer.toCharacterDomainList(): Character = results.map {
    it.run {
        Character(
            id,
            name!!,
            description!!,
        )
    }
}*/