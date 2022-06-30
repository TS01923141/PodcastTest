package com.example.podcasttest.model.network

import com.example.podcasttest.model.data.RssResponse
import com.example.podcasttest.model.data.SimpleXmlRss
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SoundcloudXmlService {
//    @GET("users/soundcloud:users:{id}/sounds.rss")
//    suspend fun getRssBySimpleXml(
//        @Path("id") id: String,
//        @Query("before") before: String?
//    ): Response<SimpleXmlRss>
    @GET("users/soundcloud:users:{id}/sounds.rss")
    suspend fun getRssBySimpleXml(
        @Path("id") id: String,
        @Query("before") before: String?
    ): Response<SimpleXmlRss>
}