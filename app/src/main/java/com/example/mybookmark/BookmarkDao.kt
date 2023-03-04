package com.example.mybookmark

import androidx.room.*

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookmark: Bookmark)

    @Query("SELECT * FROM saved_bookmarks ORDER BY id DESC")
    suspend fun getAll(): List<Bookmark>

    @Query("SELECT * FROM saved_bookmarks WHERE id = :id")
    suspend fun getById(id:Long): Bookmark?

    @Delete
    suspend fun delete(bookmark: Bookmark)
}
