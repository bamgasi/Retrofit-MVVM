package com.cocolab.retrofitexam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _id = MutableLiveData<Int>()
    private val _userId = MutableLiveData<Int>()

    /**
     * switchMap 안에 있는 값이 바뀌면, 뒤쪽에 있는
     * 로직이 트리거 된다.
     * 그래서 viewModel을 호출하는 activity에서 값을 set 할 수 있는 함수로
     * 호출한다.
     */
    val albumsItem: LiveData<AlbumsItem> = Transformations
        .switchMap(_id) { id ->
            MyRepository.getAlbum(id)
        }

    val allAlbumsList: LiveData<Albums> = MyRepository.getAllAlbumList()

    val albumsList: LiveData<Albums> = Transformations
        .switchMap(_userId) { userId ->
            MyRepository.getAlbumListByUserId(userId)
        }

    fun setId(id: Int) {
        if (_id.value == id) return
        _id.value = id
    }

    fun setUserId(userId: Int) {
        if (_userId.value == userId) return
        _userId.value = userId
    }

    fun calcenJobs() {
        MyRepository.cancelJob()
    }
}