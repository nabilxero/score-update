package com.konasl.scoreupdate.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.konasl.scoreupdate.dto.Item;
import com.konasl.scoreupdate.dto.ScoreUpdateResponse;
import com.konasl.scoreupdate.model.Score;
import com.konasl.scoreupdate.repository.ScoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ScoreApiClientService {

    @Value("${score.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final ScoreRepository repository;

    public ScoreApiClientService(RestTemplate restTemplate, ScoreRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }


    public ScoreUpdateResponse fetchApiResponse(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        return parseResponse(responseEntity.getBody());
    }

    public ScoreUpdateResponse parseResponse(String response) {
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return mapper.readValue(response, ScoreUpdateResponse.class);
        } catch (JsonProcessingException ex) {
            log.error("Error message: "+ex.getMessage());
            return null;
        }
    }

    @Scheduled(fixedDelay = 60000)
    private void callScoreApiService() {
        System.err.println("------ACTION HAPPENED!------");
        try {
            ScoreUpdateResponse scoreUpdateResponse = fetchApiResponse();
            List<Item> items = scoreUpdateResponse.getChannel().getItem();
            for (Item item: items) {
                Score scoreByGuid = repository.findByGuid(item.getGuid());
                if (scoreByGuid != null) {
                    scoreByGuid.setTitle(item.getTitle());
                    scoreByGuid.setDescription(item.getDescription());
                    scoreByGuid.setLink(item.getLink());
                    scoreByGuid.setPublishedDate(scoreUpdateResponse.getChannel().getPubDate());
                    repository.save(scoreByGuid);
                } else {
                    Score score = new Score();
                    score.setGuid(item.getGuid());
                    score.setDescription(item.getDescription());
                    score.setLink(item.getLink());
                    score.setPublishedDate(scoreUpdateResponse.getChannel().getPubDate());
                    score.setTitle(item.getTitle());
                    repository.save(score);
                }
            }
        } catch (Exception ex) {
            log.error("Error message: "+ex.getMessage());
        }


    }


}
