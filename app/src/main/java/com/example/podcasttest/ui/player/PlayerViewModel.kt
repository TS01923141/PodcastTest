package com.example.podcasttest.ui.player

import android.util.Log
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.podcasttest.model.data.Episode
import com.example.podcasttest.model.repository.RssRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

private const val TAG = "PlayerViewModel"
@HiltViewModel
class PlayerViewModel @Inject constructor(private val repository: RssRepository): ViewModel() {
    val channel = repository.channel
    private val _episode : MutableStateFlow<Episode> = MutableStateFlow(Episode())
    val episode: StateFlow<Episode> = _episode
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying : StateFlow<Boolean> = _isPlaying
    private val _currentDuration = MutableStateFlow(0f)
    val currentDuration : StateFlow<Float> = _currentDuration

    suspend fun setEpisodeByPosition(position: Int) {
        _episode.emit(channel.value.episodeList[position])
    }

    suspend fun changeEpisode(episodeChange: EpisodeChange) {
        var position = episode.value.position
        if (episodeChange == EpisodeChange.NEXT && position == 0) return
        if (episodeChange == EpisodeChange.PREVIOUS && position == channel.value.episodeList.lastIndex) return
        when(episodeChange) {
            EpisodeChange.PREVIOUS -> position+=1
            EpisodeChange.NEXT -> position-=1
        }
        _episode.emit(channel.value.episodeList[position])
    }

    suspend fun setIsPlaying(boolean: Boolean) {
        _isPlaying.emit(boolean)
    }

    suspend fun changeIsPlaying() {
        _isPlaying.emit(!_isPlaying.value)
    }

    suspend fun setDuration(float: Float) {
        if (_currentDuration.value != float) _currentDuration.emit(float)
    }
}