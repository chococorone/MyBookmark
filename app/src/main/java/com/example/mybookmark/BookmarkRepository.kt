package com.example.mybookmark

import androidx.lifecycle.LiveData

class BookmarkRepository(private val bookmarkDao: BookmarkDao) {

    suspend fun insert(bookmark: Bookmark){
        bookmarkDao.insert(bookmark)
    }

    suspend fun getAll(): List<Bookmark> {
        return bookmarkDao.getAll()
    }

}