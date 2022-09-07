package com.estebanrivera.samplemovies.data.remote

import com.estebanrivera.samplemovies.domain.*

fun CharacterServer.toCharacterDomain(): Character {
    return Character(
        id = id,
        name = name!!,
        description = description!!,
        thumbNail = with(this.thumbnail) { "$path.$extension".replace("http:", "https:") },
        )
}

fun CharacterServer.toCharacterDetailsDomain(): CharacterDetails {
    return CharacterDetails(
        id = id,
        name = name!!,
        description = description!!,
        thumbNail = with(this.thumbnail) { "$path.$extension".replace("http:", "https:") },
        comics = comics!!.toComicsDomain(),
        events = events!!.toEventsDomain(),
        series = series!!.toSeriesDomain(),
        stories = stories!!.toStoriesDomain(),
        )
}


fun ComicsServer.toComicsDomain(): Comics {
    return Comics(
        available,
        collectionURI,
        items.map { items -> items.toItemsDomain() },
        returned,
    )
}

fun SeriesServer.toSeriesDomain(): Series {
    return Series(
        available,
        collectionURI,
        items.map { items -> items.toItemsDomain() },
        returned,
    )
}

fun EventsServer.toEventsDomain(): Events {
    return Events(
        available,
        collectionURI,
//        items,
        items.map { items -> items.toItemsDomain() },
        returned,
    )
}

fun StoriesServer.toStoriesDomain(): Stories {
    return Stories(
        available,
        collectionURI,
        items.map { items -> items.toItemsDomain() },
        returned,
    )
}

fun ItemsServer.toItemsDomain(): Items {
    return Items(
        resourceURI, name
    )
}




