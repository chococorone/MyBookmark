package com.example.mybookmark

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookmarkViewModel(private val mBookmarkRepository: BookmarkRepository) : ViewModel(){
    private val TAG:String = "CORONE"

    private val _mBookmarkList = MutableLiveData<List<Bookmark>>()
    val mBookmarkList: LiveData<List<Bookmark>> = _mBookmarkList

    fun loadBookmarkList() {
        viewModelScope.launch {
            Log.d(TAG, "read dao")
            _mBookmarkList.value = mBookmarkRepository.getAll()
        }
    }
}