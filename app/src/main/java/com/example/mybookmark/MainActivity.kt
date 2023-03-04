package com.example.mybookmark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.mybookmark.ui.theme.MyBookmarkTheme

class MainActivity : ComponentActivity() {
    private lateinit var bookmarkViewModel: BookmarkViewModel

    private val mBookmarkRepository by lazy{
        BookmarkRepository(BookmarkDatabase.getDatabase(this).savedBookmarkDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookmarkViewModel = ViewModelProvider(this, ViewModelFactory(mBookmarkRepository)).get(BookmarkViewModel::class.java)
        bookmarkViewModel.loadBookmarkList()

        setContent {
            MyBookmarkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BookmarkListScreen(bookmarkViewModel = bookmarkViewModel)
                }
            }
        }
    }
}

@Composable
fun BookmarkListScreen(bookmarkViewModel: BookmarkViewModel){
    val bookmarkList by bookmarkViewModel.mBookmarkList.observeAsState(listOf())

    LazyColumn {
        items(bookmarkList) { bookmark ->
            BookmarkListItem(bookmark)
            Divider(color = Color.LightGray)
        }
    }
}

@Composable
fun BookmarkListItem(bookmark: Bookmark){
    Column{
        Text(text = bookmark.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = bookmark.url);
    }
}


