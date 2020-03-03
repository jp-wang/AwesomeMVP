package io.jp.awesomemvp.model.repo.source.remote

import io.jp.awesomemvp.model.data.Avatar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface StarwarAvatarService {

    @GET("all.json")
    suspend fun getCharacterAvatars(): List<Avatar>

    companion object {
        private val API_URL = "https://cdn.rawgit.com/akabab/starwars-api/0.2.1/api/"

        fun create(): StarwarAvatarService {
            val httpClient = OkHttpClient.Builder()
            val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            return retrofit.create(StarwarAvatarService::class.java)
        }
    }
}