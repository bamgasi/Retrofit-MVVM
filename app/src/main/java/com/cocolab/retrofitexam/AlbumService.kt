package com.cocolab.retrofitexam

import retrofit2.http.*

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums(): Albums

    @GET("/albums")
    suspend fun getSortedAlbums(
        @Query("userId") userId: Int)
            : Albums

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path("id") albumId: Int): AlbumsItem

    @POST("/albums")
    suspend fun uploadAlbum(@Body album: AlbumsItem) : AlbumsItem
}