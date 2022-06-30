package com.example.podcasttest.model.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class RssResponse {
    @Attribute(name = "version") public String version;
    @Element(name = "channel") public JavaXmlChannel channel;
}

