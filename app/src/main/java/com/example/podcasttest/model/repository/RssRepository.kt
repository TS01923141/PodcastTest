package com.example.podcasttest.model.repository

import android.util.Log
import android.util.Xml
import androidx.lifecycle.LiveData
import com.example.podcasttest.model.data.Channel
import com.example.podcasttest.model.data.RssResponse
import com.example.podcasttest.model.data.SimpleXmlChannel
import com.example.podcasttest.model.data.SimpleXmlRss
import com.example.podcasttest.model.extension.readChannel
import com.example.podcasttest.model.network.SoundcloudService
import com.example.podcasttest.model.network.SoundcloudXmlService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParser
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

private const val TAG = "RssRepository"
class RssRepository @Inject constructor(private val service: SoundcloudService, private val xmlService: SoundcloudXmlService) {
    private var _channel = MutableStateFlow(Channel())
    val channel: StateFlow<Channel> = _channel

    suspend fun refreshChannel(id: String, before: String?) = withContext(Dispatchers.IO) {
        val rss = getRss(id, before)
        rss?.printAtr()
        rss?.channel?.printAll()
//        Log.d(TAG, "refreshChannel: rss?.channel?.title: " + rss?.channel?.title)
//        Log.d(TAG, "refreshChannel: rss?.channel?.date: " + rss?.channel?.date)
//        Log.d(TAG, "refreshChannel: rss?.channel?.coverTitle: " + rss?.channel?.coverTitle)
//        Log.d(TAG, "refreshChannel: rss?.channel?.coverUrl: " + rss?.channel?.coverUrl)
//        Log.d(TAG, "refreshChannel: rss?.channel?.coverUrl: " + rss?.channel?.image?.coverUrl)
//        rss?.channel?.episodeList?.forEach {
//            Log.d(TAG, "refreshChannel: it.title: ${it.title}, date: ${it.date}, coverUrl: ${it.coverUrl}, summary: ${it.summary}, position: ${it.position}, soundSourceUrl: ${it.soundSourceUrl}")
//        }
        val response = getRssString(id, before) ?: return@withContext
        _channel.emit(parseXmlToChannel(response))
    }

    private suspend fun parseXmlToChannel(xmlString: String): Channel {
        val parser: XmlPullParser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(xmlString.byteInputStream(), null)
        parser.next()

        return parser.readChannel(parser)
    }

    private suspend fun getRssString(id: String, before: String?) : String? =
        try {
            val response = service.getRss(id, before)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: UnknownHostException) {
            e.printStackTrace()
            null
        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
            null
        } catch (e: SSLHandshakeException) {
            e.printStackTrace()
            null
        }

    private suspend fun getRss(id: String, before: String?) : SimpleXmlRss? =
        try {
            val response = xmlService.getRssBySimpleXml(id, before)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: UnknownHostException) {
            e.printStackTrace()
            null
        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
            null
        } catch (e: SSLHandshakeException) {
            e.printStackTrace()
            null
        }
}