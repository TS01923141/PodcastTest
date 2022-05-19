package com.example.podcasttest.model.repository

import android.util.Log
import android.util.Xml
import androidx.lifecycle.LiveData
import com.example.podcasttest.model.data.Channel
import com.example.podcasttest.model.extension.readChannel
import com.example.podcasttest.model.network.SoundcloudService
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
class RssRepository @Inject constructor(private val service: SoundcloudService) {
    private var _channel = MutableStateFlow(Channel())
    val channel: StateFlow<Channel> = _channel

    suspend fun refreshChannel(id: String, before: String?) = withContext(Dispatchers.IO) {
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
}