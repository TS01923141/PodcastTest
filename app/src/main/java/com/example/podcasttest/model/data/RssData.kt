package com.example.podcasttest.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Channel(
    var title: String = "",
    var coverUrl: String = "",
    var episodeList: List<Episode> = mutableListOf()
) {
    companion object{
        val fakeData = Channel(
            title = "科技島讀",
            coverUrl = "https://i1.sndcdn.com/avatars-000326154119-ogb1ma-original.jpg",
            episodeList = listOf(Episode.fakeData, Episode.fakeData, Episode.fakeData)
        )
    }
}

@Parcelize
data class Episode(
    val title: String = "",
    val date: String = "",
    val coverUrl: String = "",
    val summary: String = "",
    var previous: Episode? = null,
    var next: Episode? = null
) : Parcelable {
    companion object{
        val fakeData = Episode(
            title = "Ep.145 英雄旅程最終章",
            date = "Sun, 30 May 2021 22:00:23",
            coverUrl = "https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg",
            summary = "科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角一開始努力的培養獨特的能力，奮力不被電腦替代。接著他走上創業之路。其創辦的企業一路成長，掙脫價值鏈的限制，建立護城河，最終成為壟斷性的巨頭。此時國家開始打擊他的勢力，而人民的反彈也越來越高。他也突然發現自己享受了科技的果實，卻似乎也失去了最珍貴的東西。\n" + "\n" + "文章：小華不平凡的科技旅程",
            previous = null,
            next = null
        )
    }
}