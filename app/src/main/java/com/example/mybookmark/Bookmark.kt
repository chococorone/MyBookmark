package com.example.mybookmark

import androidx.room.*

@Entity(tableName = "saved_bookmarks")
data class Bookmark(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val url: String
)


