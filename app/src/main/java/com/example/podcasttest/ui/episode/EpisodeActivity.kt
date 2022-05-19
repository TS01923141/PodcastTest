package com.example.podcasttest.ui.episode

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.podcasttest.R
import com.example.podcasttest.model.data.Episode
import com.example.podcasttest.ui.theme.PodcastTestTheme

class EpisodeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val channelName = intent.getStringExtra("channel_name")
        val episode = intent.getParcelableExtra<Episode>("episode")
        if (channelName.isNullOrEmpty() || episode == null) {
            Toast.makeText(this, getString(R.string.data_error), Toast.LENGTH_SHORT).show()
            finish()
        }
        setContent {
            PodcastTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    EpisodeScreen(channelName = channelName!!, episode = episode!!)
                }
            }
        }
    }
}