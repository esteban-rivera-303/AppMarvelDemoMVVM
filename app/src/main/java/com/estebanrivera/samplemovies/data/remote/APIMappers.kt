package com.estebanrivera.samplemovies.data.remote

import com.estebanrivera.samplemovies.domain.Character

fun CharacterServer.toCharacterDomain(): Character {
    return Character(
        id = id,
        name = name!!,
        description = description!!,
        thumbNail = with(this.thumbnail) { "$path.$extension" },

    )
}

