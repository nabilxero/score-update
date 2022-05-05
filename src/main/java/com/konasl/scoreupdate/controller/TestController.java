package com.konasl.scoreupdate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.konasl.scoreupdate.service.ScoreApiClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    private final ScoreApiClientService service;

    public TestController(ScoreApiClientService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public void Test() {
        service.processScoreApiResponse();
    }
}
