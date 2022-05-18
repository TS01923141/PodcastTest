package com.example.podcasttest.model.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SoundcloudService {
//    users/soundcloud:users:322164009/sounds.rss
//    soundcloud:users:322164009/sounds.rss?before=335052773
    @GET("users/soundcloud:users:{id}/sounds.rss")
    suspend fun getRss(
        @Path("id") id: String,
        @Query("before") before: String?
    ): Response<String>
}