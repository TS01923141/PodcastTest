package com.example.podcasttest.ui.episode

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.podcasttest.R
import com.example.podcasttest.model.data.Channel
import com.example.podcasttest.model.data.Episode
import com.example.podcasttest.ui.theme.PodcastTestTheme

@Composable
fun EpisodeScreen(channelName: String, episode: Episode, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(model = R.drawable.ic_baseline_play_circle_outline_24)
    Column(modifier = modifier
        .padding(start = 8.dp, end = 8.dp)
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(8.dp))
        EpisodeCover(channelName, episode)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = episode.summary, style = MaterialTheme.typography.body2.copy(color = Color.Black.copy(alpha = 0.7f)))
        Spacer(modifier = Modifier.height(16.dp))
        Icon(painter = painter, contentDescription = null, tint = MaterialTheme.colors.primary, modifier = Modifier.fillMaxWidth().aspectRatio(2f).align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun PreviewEpisodeScreen() {
    PodcastTestTheme {
        androidx.compose.material.Surface {
            EpisodeScreen(channelName = Channel.fakeData.title, episode = Episode.fakeData)
        }
    }
}

@Composable
fun EpisodeCover(channelName: String, episode: Episode, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
        AsyncImage(model = episode.coverUrl, contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f))
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = channelName, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(48.dp))
            Text(text = episode.title, style = MaterialTheme.typography.h6.copy(color = Color.Black.copy(alpha = 0.6f)))
        }
    }
}

@Preview
@Composable
fun PreviewEpisodeCover() {
    PodcastTestTheme {
        androidx.compose.material.Surface {
            EpisodeCover(channelName = Channel.fakeData.title, episode = Episode.fakeData)
        }
    }
}