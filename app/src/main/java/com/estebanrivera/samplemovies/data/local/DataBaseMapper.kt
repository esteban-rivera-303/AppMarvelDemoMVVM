package com.estebanrivera.samplemovies.data.local

import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails

fun CharacterEntity.toCharacterDomain(): Character {
    return Character(
        id = id,
        name = name!!,
        description = description!!,
        thumbNail = thumbnail!!
    )
}

fun Character.toCharacterEntity() = CharacterEntity(
    id,
    name,
    description,
    thumbNail
)

fun CharacterDetails.toCharacterEntity() = CharacterEntity(
    id,
    name,
    description,
    thumbNail
)