package com.cocolab.retrofitexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var retService: AlbumService
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.allAlbumsList.observe(this, {
            it?.forEach { albumsItem ->
                val result = "Album id: ${albumsItem.id}" +"\n" +
                        "Album title: ${albumsItem.title}" +"\n" +
                        "userID: ${albumsItem.userId}" +"\n\n"
                text_view.append(result)
            }
        })
        /*retService = RetrofitInstance
                .getRetrofitInstance()
                .create(AlbumService::class.java)*/

        //getRequestWithPathParameters()
        //getRequestWithQueryParameters()
        //uploadAlbum()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.calcenJobs()
    }

    private fun getRequestWithQueryParameters() {
        viewModel.albumsList.observe(this, {
            it?.forEach { albumsItem ->
                val result = "Album id: ${albumsItem.id}" +"\n" +
                        "Album title: ${albumsItem.title}" +"\n" +
                        "userID: ${albumsItem.userId}" +"\n\n"
                text_view.append(result)
            }
        })
        viewModel.setUserId(3)

        /*val responseLiveData:LiveData<Response<Albums>> = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response)
        }
        responseLiveData.observe(this, {
            val albumsList = it.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    Log.d("MYTAG", albumsItem.title)
                    val result = "Album id: ${albumsItem.id}" +"\n" +
                            "Album title: ${albumsItem.title}" +"\n" +
                            "userID: ${albumsItem.userId}" +"\n\n"
                    text_view.append(result)
                }
            }
        })*/
    }

    fun getRequestWithPathParameters() {
        // path parameter example
        /*val pathResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, {
            val albumItem = it.body()
            val title = it.body()?.title

            Toast.makeText(this, albumItem?.toString(), Toast.LENGTH_SHORT).show()
        })*/

        viewModel.albumsItem.observe(this, {
            println("DEBUG: $it")
        })
        viewModel.setId(3)
    }

    private fun uploadAlbum() {
        val newAlbum = AlbumsItem(
                0, "MyThtle", 100
        )
        /*val postResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.uploadAlbum(newAlbum)
            emit(response)
        }

        postResponse.observe(this, {
            val recievedItem = it.body()
            val result = "Album id: ${recievedItem?.id}" +"\n" +
                    "Album title: ${recievedItem?.title}" +"\n" +
                    "userID: ${recievedItem?.userId}" +"\n\n"
            text_view.text = result
        })*/
    }
}