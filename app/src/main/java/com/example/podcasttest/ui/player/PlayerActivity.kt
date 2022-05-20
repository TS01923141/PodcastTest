package com.example.podcasttest.ui.player

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.lifecycleScope
import com.example.podcasttest.R
import com.example.podcasttest.model.data.Episode
import com.example.podcasttest.ui.theme.PodcastTestTheme
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val TAG = "PlayerActivity"
@AndroidEntryPoint
class PlayerActivity : ComponentActivity() {
    private val viewModel: PlayerViewModel by viewModels()
    private lateinit var exoPlayer: ExoPlayer

    private val onPlayClick: () -> Unit = {
        lifecycleScope.launch {
            viewModel.changeIsPlaying()
        }
    }
    private val onEpisodeChangeClick: (EpisodeChange) -> Unit = {episodeChange ->
        lifecycleScope.launch {
            viewModel.changeEpisode(episodeChange = episodeChange)
        }
    }

    private val onCurrentDurationChange: (Float) -> Unit = { float ->
        val duration = (float * exoPlayer.duration).toLong()
        exoPlayer.seekTo(duration)
        lifecycleScope.launch {
            viewModel.setDuration(float)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initExoPlayer()

        //init episode
        val episode = intent.getParcelableExtra<Episode>("episode")
        if (episode == null) {
            Toast.makeText(this, getString(R.string.data_error), Toast.LENGTH_SHORT).show()
            finish()
        }
        lifecycleScope.launch {
            viewModel.setEpisodeByPosition(episode!!.position)
        }

        //view

        setContent {
            PodcastTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PlayerScreen(viewModel = viewModel, onPlayClick = onPlayClick, onEpisodeChangeClick = onEpisodeChangeClick, onCurrentDurationChange = onCurrentDurationChange)
                }
            }
        }

        //observe
        lifecycleScope.launch {
            viewModel.episode.collect{ episode->
                val mediaItem = MediaItem.fromUri(episode.soundSourceUrl)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                exoPlayer.play()
                viewModel.setIsPlaying(true)
            }
        }
        lifecycleScope.launch {
            viewModel.isPlaying.collect{ isPlaying ->
                if (isPlaying) {
                    exoPlayer.play()
                } else {
                    exoPlayer.pause()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (viewModel.isPlaying.value) exoPlayer.pause()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isPlaying.value) exoPlayer.play()
    }

    override fun onDestroy() {
        exoPlayer.release()
        super.onDestroy()
    }

    private fun initExoPlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        exoPlayer.addListener(object : Player.Listener{
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when(playbackState) {
                    ExoPlayer.STATE_ENDED -> {
                        onEpisodeChangeClick.invoke(EpisodeChange.NEXT)
                    }
                }
                super.onPlayerStateChanged(playWhenReady, playbackState)
            }
        })
        lifecycleScope.launch {
            while (true) {
                viewModel.setDuration(exoPlayer.currentPosition.toFloat() / exoPlayer.duration)
                delay(1000)
            }
        }
    }
}