package com.example.podcasttest.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.podcasttest.model.data.Channel
import com.example.podcasttest.model.data.Episode
import com.example.podcasttest.ui.theme.PodcastTestTheme

@Composable
fun HomeScreen(viewModel: MainViewModel, modifier: Modifier = Modifier, onItemClick : (episode: Episode) -> Unit) {
    val channel by viewModel.channel.collectAsState()
    HomeScreen(channel = channel, modifier = modifier, onItemClick = onItemClick)
}

@Composable
fun HomeScreen(channel: Channel, modifier: Modifier = Modifier, onItemClick: (episode: Episode) -> Unit) {
    Column(modifier = modifier.fillMaxSize()) {
        AsyncImage(model = channel.coverUrl, contentScale = ContentScale.Crop, contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f))
        HomeList(episodeList = channel.episodeList, onItemClick = onItemClick)
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    PodcastTestTheme {
        androidx.compose.material.Surface {
            HomeScreen(channel = Channel.fakeData, onItemClick =  {})
        }
    }
}

@Composable
fun HomeList(episodeList: List<Episode>, modifier: Modifier = Modifier, onItemClick : (episode: Episode) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(items = episodeList) { item ->
            HomeItem(episode = item, onItemClick = onItemClick)
        }
    }
}

@Preview
@Composable
fun PreviewHomeList() {
    val episodeList = listOf(Episode.fakeData, Episode.fakeData, Episode.fakeData)
    PodcastTestTheme {
        androidx.compose.material.Surface {
            HomeList(episodeList = episodeList, onItemClick =  {})
        }
    }
}

@Composable
fun HomeItem(episode: Episode, modifier: Modifier = Modifier, onItemClick : (episode: Episode) -> Unit) {
    Row(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth()
        .height(64.dp)
        .clickable { onItemClick.invoke(episode) }) {
        AsyncImage(model = episode.coverUrl, contentDescription = null, modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = episode.title)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = episode.date)
        }
    }
}

@Preview
@Composable
fun PreviewHomeItem() {
    PodcastTestTheme {
        androidx.compose.material.Surface {
            HomeItem(episode = Episode.fakeData, onItemClick =  {})
        }
    }
}