package com.cocolab.retrofitexam

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MyRepository {
    var job: CompletableJob? = null

    fun getAlbumListByUserId(userId: Int): LiveData<Albums> {
        job = Job()
        return object: LiveData<Albums>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        var albums: Albums? = null
                        albums = RetrofitInstance.apiService.getSortedAlbums(userId)
                        withContext(Main) {
                            value = albums
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun getAllAlbumList(): LiveData<Albums> {
        job = Job()
        return object: LiveData<Albums>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        var albums: Albums? = null
                        albums = RetrofitInstance.apiService.getAlbums()
                        withContext(Main) {
                            value = albums
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun getAlbum(id: Int): LiveData<AlbumsItem> {
        job = Job()
        return object: LiveData<AlbumsItem>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val album = RetrofitInstance.apiService.getAlbum(id)
                        withContext(Main) {
                            value = album
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJob() {
        job?.cancel()
    }
}