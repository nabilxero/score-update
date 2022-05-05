package com.konasl.scoreupdate.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.konasl.scoreupdate.dto.ScoreUpdateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ScoreApiClientService {

    @Value("${score.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public ScoreApiClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public void processScoreApiResponse(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        parseResponse(responseEntity.getBody());
    }

    public void parseResponse(String response) {
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            ScoreUpdateResponse responseObject = mapper.readValue(response, ScoreUpdateResponse.class);
            responseObject.getChannel();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
