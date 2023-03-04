package com.example.mybookmark

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//exportSchemaはスキーマのバージョン履歴のバックアップを保持するかどうか
@Database(entities = [Bookmark::class], version = 1, exportSchema = false)
abstract class BookmarkDatabase : RoomDatabase(){
    abstract fun savedBookmarkDao(): BookmarkDao

    companion object{
        @Volatile
        private var instance: BookmarkDatabase? = null

        fun getDatabase(context: Context): BookmarkDatabase {
            return instance ?: synchronized(this){
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    BookmarkDatabase::class.java,
                    "saved_bookmarks"
                ).build()
                instance = database
                database
            } as BookmarkDatabase
        }
    }
}
