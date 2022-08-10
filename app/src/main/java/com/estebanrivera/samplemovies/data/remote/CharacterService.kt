package com.estebanrivera.samplemovies.data.remote

import android.util.Log
import com.estebanrivera.samplemovies.data.remote.APIConstants.GET_ALL_CHARACTERS
import com.estebanrivera.samplemovies.data.remote.APIConstants.GET_DETAILS_CHARACTER
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val HASH = "hash"
const val APIKEY = "apikey"
const val TIMESTAMP = "ts"
const val LIMIT = "limit"
const val ID = "id"

interface CharacterService {
    @GET(GET_ALL_CHARACTERS)
    suspend fun getAllCharacters(
        @Query(TIMESTAMP) ts: String,
        @Query(APIKEY) apikey: String,
        @Query(HASH) hash: String,
        @Query(LIMIT) limit: Int,
    ): retrofit2.Response<BaseResponse<List<CharacterServer>>>

    @GET(GET_DETAILS_CHARACTER)
    suspend fun getCharacterById(
        @Query(TIMESTAMP) ts: String,
        @Query(APIKEY) apikey: String,
        @Query(HASH) hash: String,
        @Query(ID) id: String
    ): Single<BaseResponse<CharacterServer>>

    object RequestInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            //val response = chain.proceed(request)

            Log.e("RequestInterceptor", "Outgoing request to ${request}")

            return chain.proceed(request)
        }
    }

    companion object {
        var retrofitService: CharacterService? = null

        fun getInstance(): CharacterService {

            val okHttpClient: OkHttpClient = HttpLoggingInterceptor().run {
                level = HttpLoggingInterceptor.Level.BODY
                Log.e("Retrofit", "Esto es retrofit")
                Log.e("Retrofit 1 -> ", this.level.toString())
                Log.e("Retrofit 2 -> ", this.level.name)


                //OkHttpClient.Builder().addInterceptor(this).build()
                OkHttpClient.Builder().addInterceptor { chain ->
                    val request: Request =
                        chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build()
                    chain.proceed(request)
                }.addInterceptor(this)
                    //.addInterceptor(RequestInterceptor)
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