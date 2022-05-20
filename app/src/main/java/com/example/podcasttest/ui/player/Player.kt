package com.example.podcasttest.ui.player

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.podcasttest.R
import com.example.podcasttest.model.data.Episode
import com.example.podcasttest.ui.theme.PodcastTestTheme

enum class EpisodeChange() {
    PREVIOUS, NEXT
}

@Composable
fun PlayerScreen(viewModel: PlayerViewModel, onPlayClick : () -> Unit, onEpisodeChangeClick: (EpisodeChange) -> Unit, onCurrentDurationChange: (Float) -> Unit) {
    val episode by viewModel.episode.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val duration by viewModel.currentDuration.collectAsState()
    PlayerScreen(episode = episode, isPlaying = isPlaying, duration = duration , onPlayClick = onPlayClick, onEpisodeChangeClick = onEpisodeChangeClick, onCurrentDurationChange = onCurrentDurationChange)
}

@Composable
fun PlayerScreen(episode: Episode, isPlaying: Boolean, duration: Float, modifier: Modifier = Modifier, onPlayClick : () -> Unit, onEpisodeChangeClick: (EpisodeChange) -> Unit, onCurrentDurationChange: (Float) -> Unit) {
    var currentDuration by remember{ mutableStateOf(0f) }
    var isSliding by remember { mutableStateOf(false) }
    Column(modifier = modifier
        .padding(start = 8.dp, end = 8.dp)
        .verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(8.dp))
        AsyncImage(model = episode.coverUrl, contentDescription =null, modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f))
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = episode.title)
        Spacer(modifier = Modifier.height(32.dp))
        Slider(value = if (isSliding) currentDuration else duration
            , onValueChange = {
                isSliding = true
                currentDuration = it }
            , onValueChangeFinished = {
                onCurrentDurationChange.invoke(currentDuration)
                isSliding = false
            })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            val previousPainter = rememberAsyncImagePainter(model = R.drawable.ic_baseline_keyboard_double_arrow_left_24)
            val playPainter = rememberAsyncImagePainter(model = if (isPlaying) R.drawable.ic_baseline_pause_circle_outline_24 else R.drawable.ic_baseline_play_circle_outline_24)
            val nextPainter = rememberAsyncImagePainter(model = R.drawable.ic_baseline_keyboard_double_arrow_right_24)
            Icon(painter = previousPainter, tint = MaterialTheme.colors.primary, contentDescription = null, modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .aspectRatio(1f)
                .clickable(onClick = { onEpisodeChangeClick.invoke(EpisodeChange.PREVIOUS) }))
            Icon(painter = playPainter, tint = MaterialTheme.colors.primary, contentDescription = null, modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterVertically)
                .aspectRatio(1f)
                .clickable(onClick = { onPlayClick.invoke() }))
            Icon(painter = nextPainter, tint = MaterialTheme.colors.primary, contentDescription = null, modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .aspectRatio(1f)
                .clickable(onClick = { onEpisodeChangeClick.invoke(EpisodeChange.NEXT) }))
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
fun PreviewPlayerScreen() {
    PodcastTestTheme {
        Surface {
            PlayerScreen(episode = Episode.fakeData, isPlaying = true, duration = 0.1f, onPlayClick =  {}, onEpisodeChangeClick =  {}, onCurrentDurationChange = {})
        }
    }
}