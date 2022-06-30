package com.example.podcasttest.model.extension

import android.util.Xml
import com.example.podcasttest.model.data.Episode
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.xmlpull.v1.XmlPullParser

@RunWith(RobolectricTestRunner::class)
class XmlPullParserKtTest() {

//                <image>
//                <url>https://i1.sndcdn.com/avatars-000326154119-ogb1ma-original.jpg</url>
//                <title>科技島讀 Podcast</title>
//                <link>https://daodu.tech</link>
//                </image>
@Test
fun `get imageUrl from xml`() {
    val inputStream =
        "<image><url>https://i1.sndcdn.com/avatars-000326154119-ogb1ma-original.jpg</url><title>科技島讀 Podcast</title><link>https://daodu.tech</link></image>"
            .byteInputStream()

    val parser: XmlPullParser = Xml.newPullParser()
    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
    parser.setInput(inputStream, null)
    parser.next()

    assertEquals(parser.readImageUrl(parser), "https://i1.sndcdn.com/avatars-000326154119-ogb1ma-original.jpg")
}

//    <item>
//      <guid isPermaLink="false">tag:soundcloud,2010:tracks/1058503534</guid>
//      <title>Ep.145 英雄旅程最終章</title>
//      <pubDate>Sun, 30 May 2021 22:00:23 +0000</pubDate>
//      <link>https://soundcloud.com/daodutech/podcast-final-chapter-hero-journey</link>
//      <itunes:duration>00:44:12</itunes:duration>
//      <itunes:author>Daodu Tech</itunes:author>
//      <itunes:explicit>no</itunes:explicit>
//      <itunes:summary>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角一開始努力的培養獨特的能力，奮力不被電腦替代。接著他走上創業之路。其創辦的企業一路成長，掙脫價值鏈的限制，建立護城河，最終成為壟斷性的巨頭。此時國家開始打擊他的勢力，而人民的反彈也越來越高。他也突然發現自己享受了科技的果實，卻似乎也失去了最珍貴的東西。
//
//文章：小華不平凡的科技旅程
//https://bit.ly/34vsqcV</itunes:summary>
//      <itunes:subtitle>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角…</itunes:subtitle>
//      <description>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角一開始努力的培養獨特的能力，奮力不被電腦替代。接著他走上創業之路。其創辦的企業一路成長，掙脫價值鏈的限制，建立護城河，最終成為壟斷性的巨頭。此時國家開始打擊他的勢力，而人民的反彈也越來越高。他也突然發現自己享受了科技的果實，卻似乎也失去了最珍貴的東西。
//
//文章：小華不平凡的科技旅程
//https://bit.ly/34vsqcV</description>
//      <enclosure type="audio/mpeg" url="https://feeds.soundcloud.com/stream/1058503534-daodutech-podcast-final-chapter-hero-journey.mp3" length="63681028"/>
//      <itunes:image href="https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg"/>
//    </item>
    @Test
    fun `get Episode from xml`() {
        val inputStream =
            "<item> <guid isPermaLink=\"false\">tag:soundcloud,2010:tracks/1058503534</guid> <title>Ep.145 英雄旅程最終章</title> <pubDate>Sun, 30 May 2021 22:00:23 +0000</pubDate> <link>https://soundcloud.com/daodutech/podcast-final-chapter-hero-journey</link> <itunes:duration>00:44:12</itunes:duration> <itunes:author>Daodu Tech</itunes:author> <itunes:explicit>no</itunes:explicit> <itunes:summary>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角一開始努力的培養獨特的能力，奮力不被電腦替代。接著他走上創業之路。其創辦的企業一路成長，掙脫價值鏈的限制，建立護城河，最終成為壟斷性的巨頭。此時國家開始打擊他的勢力，而人民的反彈也越來越高。他也突然發現自己享受了科技的果實，卻似乎也失去了最珍貴的東西。 文章：小華不平凡的科技旅程 https://bit.ly/34vsqcV</itunes:summary> <itunes:subtitle>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角…</itunes:subtitle> <description>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角一開始努力的培養獨特的能力，奮力不被電腦替代。接著他走上創業之路。其創辦的企業一路成長，掙脫價值鏈的限制，建立護城河，最終成為壟斷性的巨頭。此時國家開始打擊他的勢力，而人民的反彈也越來越高。他也突然發現自己享受了科技的果實，卻似乎也失去了最珍貴的東西。 文章：小華不平凡的科技旅程 https://bit.ly/34vsqcV</description> <enclosure type=\"audio/mpeg\" url=\"https://feeds.soundcloud.com/stream/1058503534-daodutech-podcast-final-chapter-hero-journey.mp3\" length=\"63681028\"/> <itunes:image href=\"https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg\"/> </item>"
                .byteInputStream()

        val parser: XmlPullParser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(inputStream, null)
        parser.next()

        val episode = parser.readEpisode(parser)

        assertEquals(episode.title, "Ep.145 英雄旅程最終章")
    }

//    <?xml version='1.0' encoding='UTF-8'?>
//<rss version="2.0" xmlns:itunes="http://www.itunes.com/dtds/podcast-1.0.dtd" xmlns:atom="http://www.w3.org/2005/Atom">
//      <channel>
//        <atom:link href="https://feeds.soundcloud.com/users/soundcloud:users:322164009/sounds.rss" rel="self" type="application/rss+xml"/>
//        <atom:link href="https://feeds.soundcloud.com/users/soundcloud:users:322164009/sounds.rss?before=335052773" rel="next" type="application/rss+xml"/>
//        <title>科技島讀</title>
//        <link>https://daodu.tech</link>
//        <pubDate>Sat, 14 Aug 2021 14:39:55 +0000</pubDate>
//        <lastBuildDate>Sat, 14 Aug 2021 14:39:55 +0000</lastBuildDate>
//        <ttl>60</ttl>
//        <language>zh-tw</language>
//        <copyright>All rights reserved</copyright>
//        <webMaster>feeds@soundcloud.com (SoundCloud Feeds)</webMaster>
//        <description>科技島讀是會員制媒體，專注於分析國際事件，解讀科技、商業與社會趨勢。我們從台灣出發，因此取名「島」讀。</description>
//        <itunes:subtitle>科技島讀是會員制媒體，專注於分析國際事件，解讀科技、商業與社會趨勢。我們從台灣出發，因此取名「島」…</itunes:subtitle>
//        <itunes:owner>
//          <itunes:name>科技島讀 Podcast</itunes:name>
//          <itunes:email>hi@daodu.tech</itunes:email>
//        </itunes:owner>
//        <itunes:author>Daodu Tech</itunes:author>
//        <itunes:explicit>no</itunes:explicit>
//        <itunes:image href="https://i1.sndcdn.com/avatars-000326154119-ogb1ma-original.jpg"/>
//        <image>
//          <url>https://i1.sndcdn.com/avatars-000326154119-ogb1ma-original.jpg</url>
//          <title>科技島讀 Podcast</title>
//          <link>https://daodu.tech</link>
//        </image>
//        <itunes:category text="Business"/>
//        <item>
//      <guid isPermaLink="false">tag:soundcloud,2010:tracks/1062984568</guid>
//      <title>SP. 科技島讀請回答</title>
//      <pubDate>Sun, 06 Jun 2021 22:00:11 +0000</pubDate>
//      <link>https://soundcloud.com/daodutech/podcast-please-answer-daodu-tech</link>
//      <itunes:duration>00:54:06</itunes:duration>
//      <itunes:author>Daodu Tech</itunes:author>
//      <itunes:explicit>no</itunes:explicit>
//      <itunes:summary>在這個最後的 Q&amp;A 特輯中，兩位主持人回答聽眾對科技島讀與 podcast 最感興趣的問題。題目涵蓋寫作方法、發展特色、身心平衡，以及給迷惘的人的建議。
//
//科技島讀 podcast 歷時 4 年。周欽華要特別感謝盧郁青擔任共同主持人，一起同甘共苦。謝謝房首伊與賴佳翎認真負責，是可靠的後援。謝謝工程師 Joe 總是使命必達，維持系統的穩定。也謝謝聲音製作人陳繹方總是能化腐朽為神奇，把尷尬冷場的錄音剪接得流暢又生動。最後，謝謝聽眾對島讀的支持。
//
//SP：科技島讀周欽華與敏迪一起談寫作 — 敏迪選讀
//https://reurl.cc/NrZbRQ
//
//周欽華常用的資訊來源與受肯定的台灣個人媒體
//
//即時科技新聞
//* 科技新報
//* iThome
//* 數位時代
//* INSIDE
//* iKnow 科技產業資訊室
//
//外國即時新聞
//* Techmeme
//* Hacker News
//* 彭博（Bloomberg）
//
//個人訂閱媒體
//* Stratechery（Ben Thompson）
//* Benedict Evans
//* The Diff(Byrne Hobart）
//* Dancoland（Alex Danco）
//* MatthewBall.vc
//
//台灣個人訂閱媒體
//* Manny Li 曼報（李易鴻）
//* 科技巨頭解碼（洪岳農）
//* 王伯達觀點
//* 區塊勢（許明恩）
//* 葉郎：異聞筆記
//
//Podcast
//* The Tim Ferriss Show
//* How I Built This with Guy Raz
//* a16z
//* 馬力歐陪你喝一杯
//* 寶博朋友說
//* 百靈果 News
//* Gooaye 股癌
//* 敏迪選讀</itunes:summary>
//      <itunes:subtitle>在這個最後的 Q&amp;A 特輯中，兩位主持人回答聽眾對科技島讀與 podcast 最感興趣的問題。題目…</itunes:subtitle>
//      <description>在這個最後的 Q&amp;A 特輯中，兩位主持人回答聽眾對科技島讀與 podcast 最感興趣的問題。題目涵蓋寫作方法、發展特色、身心平衡，以及給迷惘的人的建議。
//
//科技島讀 podcast 歷時 4 年。周欽華要特別感謝盧郁青擔任共同主持人，一起同甘共苦。謝謝房首伊與賴佳翎認真負責，是可靠的後援。謝謝工程師 Joe 總是使命必達，維持系統的穩定。也謝謝聲音製作人陳繹方總是能化腐朽為神奇，把尷尬冷場的錄音剪接得流暢又生動。最後，謝謝聽眾對島讀的支持。
//
//SP：科技島讀周欽華與敏迪一起談寫作 — 敏迪選讀
//https://reurl.cc/NrZbRQ
//
//周欽華常用的資訊來源與受肯定的台灣個人媒體
//
//即時科技新聞
//* 科技新報
//* iThome
//* 數位時代
//* INSIDE
//* iKnow 科技產業資訊室
//
//外國即時新聞
//* Techmeme
//* Hacker News
//* 彭博（Bloomberg）
//
//個人訂閱媒體
//* Stratechery（Ben Thompson）
//* Benedict Evans
//* The Diff(Byrne Hobart）
//* Dancoland（Alex Danco）
//* MatthewBall.vc
//
//台灣個人訂閱媒體
//* Manny Li 曼報（李易鴻）
//* 科技巨頭解碼（洪岳農）
//* 王伯達觀點
//* 區塊勢（許明恩）
//* 葉郎：異聞筆記
//
//Podcast
//* The Tim Ferriss Show
//* How I Built This with Guy Raz
//* a16z
//* 馬力歐陪你喝一杯
//* 寶博朋友說
//* 百靈果 News
//* Gooaye 股癌
//* 敏迪選讀</description>
//      <enclosure type="audio/mpeg" url="https://feeds.soundcloud.com/stream/1062984568-daodutech-podcast-please-answer-daodu-tech.mp3" length="77951094"/>
//      <itunes:image href="https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg"/>
//    </item><item>
//      <guid isPermaLink="false">tag:soundcloud,2010:tracks/1058503534</guid>
//      <title>Ep.145 英雄旅程最終章</title>
//      <pubDate>Sun, 30 May 2021 22:00:23 +0000</pubDate>
//      <link>https://soundcloud.com/daodutech/podcast-final-chapter-hero-journey</link>
//      <itunes:duration>00:44:12</itunes:duration>
//      <itunes:author>Daodu Tech</itunes:author>
//      <itunes:explicit>no</itunes:explicit>
//      <itunes:summary>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角一開始努力的培養獨特的能力，奮力不被電腦替代。接著他走上創業之路。其創辦的企業一路成長，掙脫價值鏈的限制，建立護城河，最終成為壟斷性的巨頭。此時國家開始打擊他的勢力，而人民的反彈也越來越高。他也突然發現自己享受了科技的果實，卻似乎也失去了最珍貴的東西。
//
//文章：小華不平凡的科技旅程
//https://bit.ly/34vsqcV</itunes:summary>
//      <itunes:subtitle>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角…</itunes:subtitle>
//      <description>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角一開始努力的培養獨特的能力，奮力不被電腦替代。接著他走上創業之路。其創辦的企業一路成長，掙脫價值鏈的限制，建立護城河，最終成為壟斷性的巨頭。此時國家開始打擊他的勢力，而人民的反彈也越來越高。他也突然發現自己享受了科技的果實，卻似乎也失去了最珍貴的東西。
//
//文章：小華不平凡的科技旅程
//https://bit.ly/34vsqcV</description>
//      <enclosure type="audio/mpeg" url="https://feeds.soundcloud.com/stream/1058503534-daodutech-podcast-final-chapter-hero-journey.mp3" length="63681028"/>
//      <itunes:image href="https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg"/>
//    </item><item>
//      <guid isPermaLink="false">tag:soundcloud,2010:tracks/1054031011</guid>
//      <title>Ep.144 急速抵達 — Just Kitchen 2 年上市｜來賓 Just Kitchen 營運長吳得暉 &amp; SparkLabs Taipei 合夥人邱彥錡</title>
//      <pubDate>Sun, 23 May 2021 22:00:20 +0000</pubDate>
//      <link>https://soundcloud.com/daodutech/podcast-delivery-completed-just-kitchen-2-year-listing-special-host-just-kitchen-kent-wu-sparklab-taipei-edgar-chiu</link>
//      <itunes:duration>01:10:06</itunes:duration>
//      <itunes:author>Daodu Tech</itunes:author>
//      <itunes:explicit>no</itunes:explicit>
//      <itunes:summary>從台灣出發的雲廚房 Just Kitchen 是只做外送的餐飲平台。其創立兩年就成功的在加拿大與德國上市，震撼台灣科技業。Just Kitchen 採「中央廚房」與「衛星店」搭配的模式，兩年內快速拓展 15 家衛星廚房，創造 17 個自製品牌，並已跨足至香港與菲律賓。這一個人才濟濟的團隊是如何集結在台灣新創加速器 SparkLabs Taipei？為何選擇從台灣出發？又是如何從種子輪就跳到公開上市？
//
//特別感謝 Just Kitchen 營運長吳得暉（Kent Wu）參與討論。他在美國曾有三次成功的新創出場紀錄，包括賣掉生鮮外送新創 Milks and Eggs 給 Grubhub。
//
//特別感謝 SparkLabs Taipei 管理合夥人邱彥錡（Edgar Chiu）參與討論。他曾在惠普（HP）與 IBM 工作，後成為台灣來電資訊服務 Gogolook 的營運長，以及創立 SparkLabs Taipei。
//
//本集錄音分成兩段。第一段為邱彥錡的訪談。第二段為吳得暉的訪談。
//
//想問科技島讀 podcast 問題？請在 2021 年 6 月 1 日前寫信至  hi@daodu.tech，就有可能由主持人在節目上回答！
//
//文章：跳級上市 — 雲廚房 Just Kitchen 站上國際舞台
//https://bit.ly/3hJH8F7</itunes:summary>
//      <itunes:subtitle>從台灣出發的雲廚房 Just Kitchen 是只做外送的餐飲平台。其創立兩年就成功的在加拿大與德…</itunes:subtitle>
//      <description>從台灣出發的雲廚房 Just Kitchen 是只做外送的餐飲平台。其創立兩年就成功的在加拿大與德國上市，震撼台灣科技業。Just Kitchen 採「中央廚房」與「衛星店」搭配的模式，兩年內快速拓展 15 家衛星廚房，創造 17 個自製品牌，並已跨足至香港與菲律賓。這一個人才濟濟的團隊是如何集結在台灣新創加速器 SparkLabs Taipei？為何選擇從台灣出發？又是如何從種子輪就跳到公開上市？
//
//特別感謝 Just Kitchen 營運長吳得暉（Kent Wu）參與討論。他在美國曾有三次成功的新創出場紀錄，包括賣掉生鮮外送新創 Milks and Eggs 給 Grubhub。
//
//特別感謝 SparkLabs Taipei 管理合夥人邱彥錡（Edgar Chiu）參與討論。他曾在惠普（HP）與 IBM 工作，後成為台灣來電資訊服務 Gogolook 的營運長，以及創立 SparkLabs Taipei。
//
//本集錄音分成兩段。第一段為邱彥錡的訪談。第二段為吳得暉的訪談。
//
//想問科技島讀 podcast 問題？請在 2021 年 6 月 1 日前寫信至  hi@daodu.tech，就有可能由主持人在節目上回答！
//
//文章：跳級上市 — 雲廚房 Just Kitchen 站上國際舞台
//https://bit.ly/3hJH8F7</description>
//      <enclosure type="audio/mpeg" url="https://feeds.soundcloud.com/stream/1054031011-daodutech-podcast-delivery-completed-just-kitchen-2-year-listing-special-host-just-kitchen-kent-wu-sparklab-taipei-edgar-chiu.mp3" length="100877403"/>
//      <itunes:image href="https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg"/>
//    </item>
//    </channel>
//    </rss>
    @Test
    fun `get Channel from xml`() {
        val inputStream =
            "<?xml version='1.0' encoding='UTF-8'?> <rss version=\"2.0\" xmlns:itunes=\"http://www.itunes.com/dtds/podcast-1.0.dtd\" xmlns:atom=\"http://www.w3.org/2005/Atom\"> <channel> <atom:link href=\"https://feeds.soundcloud.com/users/soundcloud:users:322164009/sounds.rss\" rel=\"self\" type=\"application/rss+xml\"/> <atom:link href=\"https://feeds.soundcloud.com/users/soundcloud:users:322164009/sounds.rss?before=335052773\" rel=\"next\" type=\"application/rss+xml\"/> <title>科技島讀</title> <link>https://daodu.tech</link> <pubDate>Sat, 14 Aug 2021 14:39:55 +0000</pubDate> <lastBuildDate>Sat, 14 Aug 2021 14:39:55 +0000</lastBuildDate> <ttl>60</ttl> <language>zh-tw</language> <copyright>All rights reserved</copyright> <webMaster>feeds@soundcloud.com (SoundCloud Feeds)</webMaster> <description>科技島讀是會員制媒體，專注於分析國際事件，解讀科技、商業與社會趨勢。我們從台灣出發，因此取名「島」讀。</description> <itunes:subtitle>科技島讀是會員制媒體，專注於分析國際事件，解讀科技、商業與社會趨勢。我們從台灣出發，因此取名「島」…</itunes:subtitle> <itunes:owner> <itunes:name>科技島讀 Podcast</itunes:name> <itunes:email>hi@daodu.tech</itunes:email> </itunes:owner> <itunes:author>Daodu Tech</itunes:author> <itunes:explicit>no</itunes:explicit> <itunes:image href=\"https://i1.sndcdn.com/avatars-000326154119-ogb1ma-original.jpg\"/> <image> <url>https://i1.sndcdn.com/avatars-000326154119-ogb1ma-original.jpg</url> <title>科技島讀 Podcast</title> <link>https://daodu.tech</link> </image> <itunes:category text=\"Business\"/> <item> <guid isPermaLink=\"false\">tag:soundcloud,2010:tracks/1062984568</guid> <title>SP. 科技島讀請回答</title> <pubDate>Sun, 06 Jun 2021 22:00:11 +0000</pubDate> <link>https://soundcloud.com/daodutech/podcast-please-answer-daodu-tech</link> <itunes:duration>00:54:06</itunes:duration> <itunes:author>Daodu Tech</itunes:author> <itunes:explicit>no</itunes:explicit> <itunes:summary>在這個最後的 Q&amp;A 特輯中，兩位主持人回答聽眾對科技島讀與 podcast 最感興趣的問題。題目涵蓋寫作方法、發展特色、身心平衡，以及給迷惘的人的建議。 科技島讀 podcast 歷時 4 年。周欽華要特別感謝盧郁青擔任共同主持人，一起同甘共苦。謝謝房首伊與賴佳翎認真負責，是可靠的後援。謝謝工程師 Joe 總是使命必達，維持系統的穩定。也謝謝聲音製作人陳繹方總是能化腐朽為神奇，把尷尬冷場的錄音剪接得流暢又生動。最後，謝謝聽眾對島讀的支持。 SP：科技島讀周欽華與敏迪一起談寫作 — 敏迪選讀 https://reurl.cc/NrZbRQ 周欽華常用的資訊來源與受肯定的台灣個人媒體 即時科技新聞 * 科技新報 * iThome * 數位時代 * INSIDE * iKnow 科技產業資訊室 外國即時新聞 * Techmeme * Hacker News * 彭博（Bloomberg） 個人訂閱媒體 * Stratechery（Ben Thompson） * Benedict Evans * The Diff(Byrne Hobart） * Dancoland（Alex Danco） * MatthewBall.vc 台灣個人訂閱媒體 * Manny Li 曼報（李易鴻） * 科技巨頭解碼（洪岳農） * 王伯達觀點 * 區塊勢（許明恩） * 葉郎：異聞筆記 Podcast * The Tim Ferriss Show * How I Built This with Guy Raz * a16z * 馬力歐陪你喝一杯 * 寶博朋友說 * 百靈果 News * Gooaye 股癌 * 敏迪選讀</itunes:summary> <itunes:subtitle>在這個最後的 Q&amp;A 特輯中，兩位主持人回答聽眾對科技島讀與 podcast 最感興趣的問題。題目…</itunes:subtitle> <description>在這個最後的 Q&amp;A 特輯中，兩位主持人回答聽眾對科技島讀與 podcast 最感興趣的問題。題目涵蓋寫作方法、發展特色、身心平衡，以及給迷惘的人的建議。 科技島讀 podcast 歷時 4 年。周欽華要特別感謝盧郁青擔任共同主持人，一起同甘共苦。謝謝房首伊與賴佳翎認真負責，是可靠的後援。謝謝工程師 Joe 總是使命必達，維持系統的穩定。也謝謝聲音製作人陳繹方總是能化腐朽為神奇，把尷尬冷場的錄音剪接得流暢又生動。最後，謝謝聽眾對島讀的支持。 SP：科技島讀周欽華與敏迪一起談寫作 — 敏迪選讀 https://reurl.cc/NrZbRQ 周欽華常用的資訊來源與受肯定的台灣個人媒體 即時科技新聞 * 科技新報 * iThome * 數位時代 * INSIDE * iKnow 科技產業資訊室 外國即時新聞 * Techmeme * Hacker News * 彭博（Bloomberg） 個人訂閱媒體 * Stratechery（Ben Thompson） * Benedict Evans * The Diff(Byrne Hobart） * Dancoland（Alex Danco） * MatthewBall.vc 台灣個人訂閱媒體 * Manny Li 曼報（李易鴻） * 科技巨頭解碼（洪岳農） * 王伯達觀點 * 區塊勢（許明恩） * 葉郎：異聞筆記 Podcast * The Tim Ferriss Show * How I Built This with Guy Raz * a16z * 馬力歐陪你喝一杯 * 寶博朋友說 * 百靈果 News * Gooaye 股癌 * 敏迪選讀</description> <enclosure type=\"audio/mpeg\" url=\"https://feeds.soundcloud.com/stream/1062984568-daodutech-podcast-please-answer-daodu-tech.mp3\" length=\"77951094\"/> <itunes:image href=\"https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg\"/> </item><item> <guid isPermaLink=\"false\">tag:soundcloud,2010:tracks/1058503534</guid> <title>Ep.145 英雄旅程最終章</title> <pubDate>Sun, 30 May 2021 22:00:23 +0000</pubDate> <link>https://soundcloud.com/daodutech/podcast-final-chapter-hero-journey</link> <itunes:duration>00:44:12</itunes:duration> <itunes:author>Daodu Tech</itunes:author> <itunes:explicit>no</itunes:explicit> <itunes:summary>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角一開始努力的培養獨特的能力，奮力不被電腦替代。接著他走上創業之路。其創辦的企業一路成長，掙脫價值鏈的限制，建立護城河，最終成為壟斷性的巨頭。此時國家開始打擊他的勢力，而人民的反彈也越來越高。他也突然發現自己享受了科技的果實，卻似乎也失去了最珍貴的東西。 文章：小華不平凡的科技旅程 https://bit.ly/34vsqcV</itunes:summary> <itunes:subtitle>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角…</itunes:subtitle> <description>科技島讀 4 年旅程的終章，是一篇角色扮演遊戲（Role-playing game，RPG）。主角一開始努力的培養獨特的能力，奮力不被電腦替代。接著他走上創業之路。其創辦的企業一路成長，掙脫價值鏈的限制，建立護城河，最終成為壟斷性的巨頭。此時國家開始打擊他的勢力，而人民的反彈也越來越高。他也突然發現自己享受了科技的果實，卻似乎也失去了最珍貴的東西。 文章：小華不平凡的科技旅程 https://bit.ly/34vsqcV</description> <enclosure type=\"audio/mpeg\" url=\"https://feeds.soundcloud.com/stream/1058503534-daodutech-podcast-final-chapter-hero-journey.mp3\" length=\"63681028\"/> <itunes:image href=\"https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg\"/> </item><item> <guid isPermaLink=\"false\">tag:soundcloud,2010:tracks/1054031011</guid> <title>Ep.144 急速抵達 — Just Kitchen 2 年上市｜來賓 Just Kitchen 營運長吳得暉 &amp; SparkLabs Taipei 合夥人邱彥錡</title> <pubDate>Sun, 23 May 2021 22:00:20 +0000</pubDate> <link>https://soundcloud.com/daodutech/podcast-delivery-completed-just-kitchen-2-year-listing-special-host-just-kitchen-kent-wu-sparklab-taipei-edgar-chiu</link> <itunes:duration>01:10:06</itunes:duration> <itunes:author>Daodu Tech</itunes:author> <itunes:explicit>no</itunes:explicit> <itunes:summary>從台灣出發的雲廚房 Just Kitchen 是只做外送的餐飲平台。其創立兩年就成功的在加拿大與德國上市，震撼台灣科技業。Just Kitchen 採「中央廚房」與「衛星店」搭配的模式，兩年內快速拓展 15 家衛星廚房，創造 17 個自製品牌，並已跨足至香港與菲律賓。這一個人才濟濟的團隊是如何集結在台灣新創加速器 SparkLabs Taipei？為何選擇從台灣出發？又是如何從種子輪就跳到公開上市？ 特別感謝 Just Kitchen 營運長吳得暉（Kent Wu）參與討論。他在美國曾有三次成功的新創出場紀錄，包括賣掉生鮮外送新創 Milks and Eggs 給 Grubhub。 特別感謝 SparkLabs Taipei 管理合夥人邱彥錡（Edgar Chiu）參與討論。他曾在惠普（HP）與 IBM 工作，後成為台灣來電資訊服務 Gogolook 的營運長，以及創立 SparkLabs Taipei。 本集錄音分成兩段。第一段為邱彥錡的訪談。第二段為吳得暉的訪談。 想問科技島讀 podcast 問題？請在 2021 年 6 月 1 日前寫信至  hi@daodu.tech，就有可能由主持人在節目上回答！ 文章：跳級上市 — 雲廚房 Just Kitchen 站上國際舞台 https://bit.ly/3hJH8F7</itunes:summary> <itunes:subtitle>從台灣出發的雲廚房 Just Kitchen 是只做外送的餐飲平台。其創立兩年就成功的在加拿大與德…</itunes:subtitle> <description>從台灣出發的雲廚房 Just Kitchen 是只做外送的餐飲平台。其創立兩年就成功的在加拿大與德國上市，震撼台灣科技業。Just Kitchen 採「中央廚房」與「衛星店」搭配的模式，兩年內快速拓展 15 家衛星廚房，創造 17 個自製品牌，並已跨足至香港與菲律賓。這一個人才濟濟的團隊是如何集結在台灣新創加速器 SparkLabs Taipei？為何選擇從台灣出發？又是如何從種子輪就跳到公開上市？ 特別感謝 Just Kitchen 營運長吳得暉（Kent Wu）參與討論。他在美國曾有三次成功的新創出場紀錄，包括賣掉生鮮外送新創 Milks and Eggs 給 Grubhub。 特別感謝 SparkLabs Taipei 管理合夥人邱彥錡（Edgar Chiu）參與討論。他曾在惠普（HP）與 IBM 工作，後成為台灣來電資訊服務 Gogolook 的營運長，以及創立 SparkLabs Taipei。 本集錄音分成兩段。第一段為邱彥錡的訪談。第二段為吳得暉的訪談。 想問科技島讀 podcast 問題？請在 2021 年 6 月 1 日前寫信至  hi@daodu.tech，就有可能由主持人在節目上回答！ 文章：跳級上市 — 雲廚房 Just Kitchen 站上國際舞台 https://bit.ly/3hJH8F7</description> <enclosure type=\"audio/mpeg\" url=\"https://feeds.soundcloud.com/stream/1054031011-daodutech-podcast-delivery-completed-just-kitchen-2-year-listing-special-host-just-kitchen-kent-wu-sparklab-taipei-edgar-chiu.mp3\" length=\"100877403\"/> <itunes:image href=\"https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg\"/> </item> </channel> </rss>"
                .byteInputStream()

        val parser: XmlPullParser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(inputStream, null)
        parser.next()

        val channel = parser.readChannel(parser)

        assertEquals(channel.title, "科技島讀")
        assertEquals(channel.episodeList.size, 3)
    }
}