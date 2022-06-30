package com.example.podcasttest.model.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "channel", strict = false)
public class JavaXmlChannel {
    @Element
    public String title = "";
//    @Element(name = "url", required = false)
//    @Path("image")
    @Path("image/url")
    public String coverUrl = "";
    @Element(name = "pubDate")
    public String date = "";

    public void printAll() {
        System.out.println("JavaXmlChannel");
        System.out.println("title: " + title);
        System.out.println("coverUrl: " + coverUrl);
        System.out.println("date: " + date);
        System.out.println("-end-");
    }
}
