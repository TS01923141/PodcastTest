package com.example.podcasttest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podcasttest.model.repository.RssRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RssRepository): ViewModel() {
    val channel = repository.channel

    fun updateChannelRss(id: String, before: String?) = viewModelScope.launch(Dispatchers.IO) {
        repository.refreshChannel(id, before)
    }
}