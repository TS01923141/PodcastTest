package com.example.podcasttest.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.podcasttest.model.data.Channel
import com.example.podcasttest.ui.episode.EpisodeActivity
import com.example.podcasttest.ui.theme.PodcastTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import java.util.*

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PodcastTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen(viewModel = viewModel) {
                        val intent = Intent(this, EpisodeActivity::class.java)
                        intent.putExtra("channel_name", viewModel.channelName)
                        intent.putExtra("episode", it.copy(previous = null, next = null))
                        Log.d(TAG, "onCreate: viewModel.channelName: ${viewModel.channelName}")
                        startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        checkRssUpdatedTime()
    }

    private fun checkRssUpdatedTime() {
        val lastUpdatedTime = getSharedPreferences("updatedTime", MODE_PRIVATE).getLong("Rss", 0)
        if ((Calendar.getInstance().timeInMillis - lastUpdatedTime) > 5*60*1000 || viewModel.channel.value == Channel()) {
            viewModel.updateChannelRss("322164009",null)
            getSharedPreferences("updatedTime", MODE_PRIVATE)
                .edit()
                .putLong("Rss", Calendar.getInstance().timeInMillis)
                .apply()
        }
    }
}