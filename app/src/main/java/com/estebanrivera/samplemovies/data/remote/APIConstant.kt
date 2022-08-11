package com.estebanrivera.samplemovies.data.remote

object APIConstants {

    //const val BASE_API_URL = "https://developer.marvel.com/$VERSION"
    const val BASE_API_URL = "https://gateway.marvel.com/v1/public/"

    const val GET_ALL_CHARACTERS = "characters"
    const val GET_DETAILS_CHARACTER = "characters/{id}"

    const val PUBLIC_KEY = "4212fa6697aaa3ea248b31378a2dca65"
    const val PRIVATE_KEY = "052a3ad782f7708817a6b0e0f30812bcf195b063"

    //Base Response Server
    const val KEY_CODE = "code"
    const val KEY_STATUS = "status"
    const val KEY_COPYRIGHT = "copyright"
    const val KEY_ATTRIBUTION_TEXT = "attributionText"
    const val KEY_ATTRIBUTION_HTML = "attributionHTML"
    const val KEY_ETAG = "etag"
    const val KEY_DATA = "data"

    // Character
    const val KEY_CHARACTER = "Character"
    const val KEY_ID = "id"
    const val KEY_NAME = "name"
    const val KEY_IMAGE = "image"
    const val KEY_DESCRIPTION = "description"
    const val KEY_MODIFIED = "modified"
    const val KEY_RESOURCE_URI = "resourceURI"
    const val KEY_URLS = "urls"
    const val KEY_THUMBNAIL = "thumbnail"
    const val KEY_COMICS = "comics"
    const val KEY_STORIES = "stories"
    const val KEY_EVENTS = "events"
    const val KEY_SERIES = "series"

    //URL
    const val KEY_URL_TYPE = "type"
    const val KEY_URL_IMAGE = "url"

    // IMAGE
    const val KEY_IMAGE_EXTENSION = "extension"
    const val KEY_IMAGE_PATH = "path"

    //
    const val KEY_AVAILABLE = "available"
    const val KEY_COLLECTIONS_URI = "collectionURI"
    const val KEY_RETURNED = "returned"
    const val KEY_ITEMS = "items"
}

/*

Authentication for Client-Side Applications
Requests from client-side (browser-based) applications must originate from a pre-authorized web site or browser extension URL.

You may add or edit your authorized domains in your API account panel. You may use the “*” wildcard to denote subdomains or paths. For example:

marvel.com - will authorize requests from Marvel.com but no subdomains of Marvel.com
developer.marvel.com - will authorize requests from developer.marvel.com
*.marvel.com - will authorize requests from any Marvel.com subdomain as well as Marvel.com
*.marvel.com/apigateway - will authorize requests from the apigateway path on any Marvel.com subdomain as well as Marvel.com
Authentication for Server-Side Applications
Server-side applications must pass two parameters in addition to the apikey parameter:

ts - a timestamp (or other long string which can change on a request-by-request basis)
hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
For example, a user with a public key of "1234" and a private key of "abcd" could construct a valid call as follows: http://gateway.marvel.com/v1/public/comics?ts=1&apikey=1234&hash=ffd275c5130566a2916217b101f26150 (the hash value is the md5 digest of 1abcd1234)
 */
