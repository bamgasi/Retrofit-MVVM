package com.cocolab.retrofitexam

import com.google.gson.annotations.SerializedName

/**
 * Response Json Item
 */
data class AlbumsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)