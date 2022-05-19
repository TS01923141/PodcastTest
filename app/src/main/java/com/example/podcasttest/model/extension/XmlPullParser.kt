package com.example.podcasttest.model.extension

import android.util.Xml
import com.example.podcasttest.model.data.Channel
import com.example.podcasttest.model.data.Episode
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

//fun newParserInstance(inputStream: InputStream): XmlPullParser {
//    val parser: XmlPullParser = Xml.newPullParser()
//    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
//    parser.setInput(inputStream, null)
//    parser.next()
//    return parser
//}

@Throws(XmlPullParserException::class, IOException::class)
fun XmlPullParser.readChannel(parser: XmlPullParser): Channel {
    var title = ""
    var coverUrl = ""
    val episodeList = mutableListOf<Episode>()
    while(parser.next() != XmlPullParser.END_TAG && parser.name != "rss") {
        if (parser.eventType != XmlPullParser.START_TAG) {
            continue
        }
        when(parser.name) {
            //Channel
            "channel" -> parser.next()
            //title
            "title" -> title = readText(parser, "", "title")
            //coverUrl
            "image" -> coverUrl = readImageUrl(parser)
            //Episode
            "item" -> {
                val episode = readEpisode(parser)
                //previous
                episode.previous = episodeList.lastOrNull()
                //next
                episodeList.lastOrNull()?.next = episode
                episodeList.add(episode)
            }
            else -> skip(parser)
        }
    }
    return Channel(title, coverUrl, episodeList)
}

@Throws(XmlPullParserException::class, IOException::class)
fun XmlPullParser.readImageUrl(parser: XmlPullParser): String {
    var imageUrl = ""
    parser.require(XmlPullParser.START_TAG, "", "image")
    while (parser.next() != XmlPullParser.END_TAG && parser.name != "image") {
        if (parser.eventType != XmlPullParser.START_TAG) {
            continue
        }
        when(parser.name) {
            "url" -> imageUrl = readText(parser)
            else -> skip(parser)
        }
    }
    parser.require(XmlPullParser.END_TAG, "", "image")
    return imageUrl
}

@Throws(XmlPullParserException::class, IOException::class)
fun XmlPullParser.readEpisode(parser: XmlPullParser): Episode {
    var title = ""
    var date = ""
    var coverUrl = ""
    var summary = ""
    while (parser.next() != XmlPullParser.END_TAG && parser.name != "item") {
        if (parser.eventType != XmlPullParser.START_TAG) {
            continue
        }
        when(parser.name) {
            //title
            "title" -> title = readText(parser, "", "title")
            //date
            "pubDate" ->{
                //在此轉換成正確TimeZone跟format
//                val dateString = readTitle(parser, "", "pubDate")
//                //Sun, 06 Jun 2021 22:00:11 +0000
//                val simpleDateFormat = SimpleDateFormat("")
                date = readText(parser, "", "pubDate")
            }
            //coverUrl
            "itunes:image" -> {
                parser.require(XmlPullParser.START_TAG, "", "itunes:image")
                coverUrl = parser.getAttributeValue("", "href")
                parser.nextTag()
                parser.require(XmlPullParser.END_TAG, "", "itunes:image")
            }
            //summary
            "itunes:summary" -> summary = readText(parser, "", "itunes:summary")
            else -> skip(parser)
        }
    }
    return Episode(title, date, coverUrl, summary)
}

// Processes title tags in the feed.
//                <title>科技島讀</title>
@Throws(IOException::class, XmlPullParserException::class)
private fun XmlPullParser.readText(parser: XmlPullParser, nameSpace: String?, name: String): String {
    parser.require(XmlPullParser.START_TAG, nameSpace, name)
    val title = readText(parser)
    parser.require(XmlPullParser.END_TAG, nameSpace, name)
    return title
}

// For the tags title and summary, extracts their text values.
@Throws(IOException::class, XmlPullParserException::class)
private fun XmlPullParser.readText(parser: XmlPullParser): String {
    var result = ""
    if (parser.next() == XmlPullParser.TEXT) {
        result = parser.text
        parser.nextTag()
    }
    return result
}

@Throws(XmlPullParserException::class, IOException::class)
private fun XmlPullParser.skip(parser: XmlPullParser) {
    if (parser.eventType != XmlPullParser.START_TAG) {
        throw IllegalStateException()
    }
    var depth = 1
    while (depth != 0) {
        when (parser.next()) {
            XmlPullParser.END_TAG -> depth--
            XmlPullParser.START_TAG -> depth++
        }
    }
}