package com.example.podcasttest.model.data

import android.media.Image
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.simpleframework.xml.*

//疑似name有冒號或內容有http://就轉不成功
//有冒號：就會失敗?

@Root(name = "rss", strict = false)
class SimpleXmlRss{
    @field:Attribute var version: String = ""
    @field:Attribute(name = "xmlns:itunes", required = false) var itunes: String = ""
    @field:Attribute(name = "xmlns:atom", required = false) var atom: String = ""
    @field:Element var channel: SimpleXmlChannel = SimpleXmlChannel()

    fun printAtr() {
        println("SimpleXmlRss")
        println("version: $version")
        println("itunes: $itunes")
        println("atom: $atom")
    }
}

@Root(name = "channel", strict = false)
class SimpleXmlChannel{
    @field:Path("atom:link[1]") var firstLink : SimpleXmlLink = SimpleXmlLink()
    @field:Path("atom:link[2]") var secondLink : SimpleXmlLink = SimpleXmlLink()
    @field:Element var title: String = ""
    @field:Element(required = false) var link: String = ""
    @field:Element(name = "pubDate") var date: String = ""
    @field:Element(required = false) var buildDate: String = ""
    @field:Element var ttl: String = ""
    @field:Element var language: String = ""
    @field:Element var copyright: String = ""
    @field:Element var webMaster: String = ""
    @field:Element var description: String = ""
    @field:Element(name = "itunes:subtitle", required = false) var subtitle = ""
    @field:Element(name = "itunes:owner", required = false) var owner = SimpleXmlOwner()
    @field:Element(name = "itunes:author", required = false) var author: String = ""
    @field:Element(name = "itunes:explicit", required = false) var explicit : String = ""
    @field:Attribute(name = "href", required = false) @Path("itunes:image") var imageHref: String = ""
    @field:Element(name = "image") @Path("rss/channel") var image: SimpleXmlImage = SimpleXmlImage()
//    @Path("image") @Text(required = false) var image: SimpleXmlImage = SimpleXmlImage()
//    @field:Element(name = "url", required = false) @field:Path("image")  var coverUrl: String = ""
//    @field:Path("image/title") var imageTitle: String = ""
//    @field:Path("image/link") var imageLink: String = ""
    @field:Element(name ="text", required = false) @Path("itunes:category") var category = ""
//    @field:Attribute(name = "isPermaLink") @Path("guid") var isPermaLink = ""
//    @field:Element(name = "guid") var guid = ""
//    @field:Path("image/url") var coverUrl: String = ""
    @field:ElementList(inline = true) var episodeList: List<SimpleXmlEpisode> = mutableListOf()

    fun printAll() {
        println("SimpleXmlChannel")
        firstLink.apply {
            println("firstLink.href: $href")
            println("firstLink.rel: $rel")
            println("firstLink.type: $type")
        }
        secondLink.apply {
            println("secondLink.href: $href")
            println("secondLink.rel: $rel")
            println("secondLink.type: $type")
        }
        println("title: $title")
        println("link: $link")
        println("date: $date")
        println("buildDate: $buildDate")
        println("ttl: $ttl")
        println("language: $language")
        println("copyright: $copyright")
        println("webMaster: $webMaster")
        println("description: $description")
        println("subtitle: $subtitle")
        owner.apply {
            println("owner.name: ${name}")
            println("owner.email: ${email}")
        }
        println("author: $author")
        println("explicit: $explicit")
        println("imageHref: $imageHref")
        image.apply {
            println("image.url: $url")
            println("image.title: $title")
            println("image.link: $link")
        }
//        println("coverUrl: $coverUrl")
//        println("imageTitle: $imageTitle")
//        println("imageLink: $imageLink")
        println("category: $category")
//        episodeList.forEach {
//            println("episode.title: ${it.title}")
//            println("episode.coverUrl: ${it.coverUrl}")
//            println("episode.date: ${it.date}")
//            println("episode.position: ${it.position}")
//            println("episode.summary: ${it.summary}")
//            println("episode.soundSourceUrl: ${it.soundSourceUrl}")
//        }
        println("-end-")
    }
}

@Root(name = "image", strict = false)
class SimpleXmlImage{
    @field:Element(required = false) var url: String = ""
    @field:Element(required = false) var title: String = ""
    @field:Element(required = false) var link: String = ""
}

@Root(name = "atom:link", strict = false)
class SimpleXmlLink{
    @field:Attribute var href: String = ""
    @field:Attribute var rel: String = ""
    @field:Attribute var type: String = ""
}

@Root(name = "itunes:owner", strict = false)
class SimpleXmlOwner{
    @field:Attribute(name = "itunes:name") var name: String = ""
    @field:Attribute(name = "itunes:email") var email: String = ""
}

//@Root(name = "channel", strict = false)
//data class SimpleXmlChannel(
//    @field:Element @param:Element var title: String = "",
//    @field:Element(name = "pubDate") @param:Element(name = "pubDate") var date: String = "",
//    @field:Element(name = "url", required = false) @param:Element(name = "url", required = false) @field:Path("image")  var coverUrl: String = "",
//    @field:ElementList(inline = true) @param:ElementList(inline = true) var episodeList: List<SimpleXmlEpisode> = mutableListOf(),
//)

//@Root(name = "image", strict = false)
//class SimpleXmlImage{
//    @field:Element(name = "url") var coverUrl: String = ""
//}

@Root(name = "item", strict = false)
class SimpleXmlEpisode{
    @field:Element var title: String = ""
    @field:Element(name = "pubDate") var date: String = ""
    @field:Element(name = "href", required = false) @field:Path("itunes:image") var coverUrl: String = ""
    @field:Element(name = "itunes:summary", required = false) var summary: String = ""
    var position: Int = -1
    @field:Element(name = "url", required = false) @field:Path("enclosure") var soundSourceUrl: String = ""
}

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
    var position: Int = -1,
    val soundSourceUrl: String = ""
) : Parcelable {
    companion object{
        val fakeData = Episode(
            title = "Ep.145 英雄旅程最終章",
            date = "Sun, 30 May 2021 22:00:23",
            coverUrl = "https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg",
            summary = "科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角一開始努力的培養獨特的能力，奮力不被電腦替代。接著他走上創業之路。其創辦的企業一路成長，掙脫價值鏈的限制，建立護城河，最終成為壟斷性的巨頭。此時國家開始打擊他的勢力，而人民的反彈也越來越高。他也突然發現自己享受了科技的果實，卻似乎也失去了最珍貴的東西。\n" + "\n" + "文章：小華不平凡的科技旅程",
            soundSourceUrl = "https://feeds.soundcloud.com/stream/1062984568-daodutech-podcast-please-answer-daodu-tech.mp3"
        )
    }
}