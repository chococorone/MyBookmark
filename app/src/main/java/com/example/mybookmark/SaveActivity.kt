package com.example.mybookmark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class SaveActivity : AppCompatActivity(),CoroutineScope by MainScope() {
    private val TAG:String = "CORONE"

    private val mBookmarkRepository by lazy{
        BookmarkRepository(BookmarkDatabase.getDatabase(this).savedBookmarkDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save2)

        val intent = intent
        val title = intent.getStringExtra(Intent.EXTRA_SUBJECT).toString()
        val url = intent.getStringExtra(Intent.EXTRA_TEXT).toString()
        Log.d(TAG, title)
        Log.d(TAG, url)

        val bookmark = Bookmark(title=title, url = url)

        launch(Dispatchers.IO){
            mBookmarkRepository.insert(bookmark)
            Log.d(TAG, "complete insert")
            finish()
        }


        Log.d(TAG, "end of oncreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel() //coroutineスコープの開放
        Log.d(TAG, "end of ondestroy")
    }

}