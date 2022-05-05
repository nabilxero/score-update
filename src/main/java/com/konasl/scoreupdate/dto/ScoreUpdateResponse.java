package com.konasl.scoreupdate.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "rss")
public class ScoreUpdateResponse implements Serializable {

    @JacksonXmlProperty(isAttribute = true, localName = "channel")
    private Channel channel;
}
