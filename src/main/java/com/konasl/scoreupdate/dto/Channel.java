package com.konasl.scoreupdate.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "channel")
public class Channel implements Serializable {

    @JacksonXmlProperty(localName = "title")
    private String title;

    @JacksonXmlProperty(localName = "ttl")
    private int ttl;

    @JacksonXmlProperty(localName = "link")
    private String link;

    @JacksonXmlProperty(localName = "description")
    private String description;

    @JacksonXmlProperty(localName = "copyright")
    private String copyright;

    @JacksonXmlProperty(localName = "language")
    private String language;

    @JacksonXmlProperty(localName = "pubDate")
    private Date pubDate;

    @JacksonXmlProperty(isAttribute = true,localName = "item")
    @JacksonXmlElementWrapper(useWrapping=false)
    private List<Item> item;
}
