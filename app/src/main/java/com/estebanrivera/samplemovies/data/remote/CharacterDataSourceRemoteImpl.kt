package com.estebanrivera.samplemovies.data.remote

import android.content.Context
import android.graphics.Bitmap

import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.palette.graphics.Palette
import com.estebanrivera.samplemovies.R
import com.estebanrivera.samplemovies.data.local.ColorCharacterEntity
import com.estebanrivera.samplemovies.data.remote.APIConstants.PRIVATE_KEY
import com.estebanrivera.samplemovies.data.remote.APIConstants.PUBLIC_KEY
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails
import com.estebanrivera.samplemovies.util.getTimestamp
import com.estebanrivera.samplemovies.util.toBitmap
import com.estebanrivera.samplemovies.util.toHash
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.URL
import javax.inject.Inject


class CharacterDataSourceRemoteImpl @Inject constructor(
    private val characterService: CharacterService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val context: Context
) :
    CharacterDataSourceRemote {

    override suspend fun getAllCharacter(limit: Int, offset: Int): ResultWrapper<List<Character>> {
        val ts = getTimestamp()
        val hash = toHash(ts, PUBLIC_KEY, PRIVATE_KEY)

        return safeApiCall(dispatcher) {
            characterService.getAllCharacters(ts, PUBLIC_KEY, hash, limit, offset)
                .body()!!.data.results.map { characterServer -> characterServer.toCharacterDomain() }
        }
    }

    override suspend fun getCharacterById(id: String): ResultWrapper<CharacterDetails> {
        val ts = getTimestamp()
        val hash = toHash(ts, PUBLIC_KEY, PRIVATE_KEY)
        return safeApiCall(dispatcher) {
            characterService.getCharacterById(id, ts, PUBLIC_KEY, hash)
                .body()!!.data.results.first().toCharacterDetailsDomain()
        }
    }

    override suspend fun getColorPredominantCharacter(id: Int, url: String): ColorCharacterEntity =
        withContext(dispatcher) {
            val urlImage = URL(url)
            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }
            getColorPredominantFromBitmap(id, generatePalette(result.await()!!).invoke())

        }

    private fun generatePalette(bitmap: Bitmap) =  {
        Palette.Builder(bitmap).generate ()
    }

    private fun getColorPredominantFromBitmap(id: Int, palette: Palette) = run {
        val dominantColor = palette.getDominantColor(
            ContextCompat.getColor(
                context,
                R.color.primary
            )
        )
        ColorCharacterEntity(id, dominantColor)
    }
}