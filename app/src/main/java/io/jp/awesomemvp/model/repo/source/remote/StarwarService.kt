package io.jp.awesomemvp.model.repo.source.remote

import io.jp.awesomemvp.model.data.CharacterResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StarwarService {
    @GET("people")
    suspend fun searchCharacters(@Query("search") search: String): CharacterResult

    @GET("people")
    suspend fun getAllCharacters(): CharacterResult

    companion object {
        private val API_URL = "https://swapi.co/api/"

        fun create(): StarwarService {
            val httpClient = OkHttpClient.Builder()
            val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            return retrofit.create(StarwarService::class.java)
        }
    }
}