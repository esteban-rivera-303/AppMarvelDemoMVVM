package com.estebanrivera.samplemovies.data.remote

import com.estebanrivera.samplemovies.data.remote.APIConstants.GET_ALL_CHARACTERS
import com.estebanrivera.samplemovies.data.remote.APIConstants.GET_DETAILS_CHARACTER
import com.estebanrivera.samplemovies.util.Constants.APIKEY
import com.estebanrivera.samplemovies.util.Constants.HASH
import com.estebanrivera.samplemovies.util.Constants.ID
import com.estebanrivera.samplemovies.util.Constants.LIMIT
import com.estebanrivera.samplemovies.util.Constants.OFFSET
import com.estebanrivera.samplemovies.util.Constants.TIMESTAMP
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CharacterService {

    @GET(GET_ALL_CHARACTERS)
    suspend fun getAllCharacters(
        @Query(TIMESTAMP) ts: String,
        @Query(APIKEY) apikey: String,
        @Query(HASH) hash: String,
        @Query(LIMIT) limit: Int,
        @Query(OFFSET) offset: Int,
    ): Response<BaseResponse<List<CharacterServer>>>

    @GET(GET_DETAILS_CHARACTER)
    suspend fun getCharacterById(
        @Path(ID) id: String,
        @Query(TIMESTAMP) ts: String,
        @Query(APIKEY) apikey: String,
        @Query(HASH) hash: String,
        //@Query(ID) id: String
    ): Response<BaseResponse<List<CharacterServer>>>


    companion object {

        var retrofitService: CharacterService? = null

        fun getInstance(): CharacterService {

            val okHttpClient: OkHttpClient = HttpLoggingInterceptor().run {
                level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder().addInterceptor { chain ->
                    val request: Request =
                        chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build()
                    chain.proceed(request)
                }.addInterceptor(this)
                    .build()

            }
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(APIConstants.BASE_API_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(CharacterService::class.java)
            }
            return retrofitService!!
        }
    }
}