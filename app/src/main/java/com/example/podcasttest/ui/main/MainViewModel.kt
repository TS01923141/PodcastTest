package com.example.podcasttest.ui.main

import androidx.lifecycle.ViewModel
import com.example.podcasttest.model.repository.RssRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RssRepository): ViewModel() {
    fun updateChannelRss() {

    }
}