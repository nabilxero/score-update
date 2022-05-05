package com.konasl.scoreupdate.controller;

import com.konasl.scoreupdate.dto.ApiResponse;
import com.konasl.scoreupdate.model.Score;
import com.konasl.scoreupdate.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/score-update")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService service;

    @GetMapping("/get-recent-scores")
    public ResponseEntity<?> getRecentScores() {
        List<Score> recentScoreList = service.getRecentScoreList();
        if (recentScoreList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", 200, recentScoreList));
        } else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Failure", 500));
    }

}
