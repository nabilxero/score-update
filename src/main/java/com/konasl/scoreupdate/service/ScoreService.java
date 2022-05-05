package com.konasl.scoreupdate.service;

import com.konasl.scoreupdate.model.Score;
import com.konasl.scoreupdate.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreService {

    private final ScoreRepository repository;

    public List<Score> getScoreList() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            log.error("Error message: "+ex.getMessage());
            return null;
        }
    }
    public List<Score> getRecentScoreList() {
        try {
            return repository.findAllByOrderByPublishedDateDesc();
        } catch (Exception ex) {
            log.error("Error message: "+ex.getMessage());
            return null;
        }
    }


}
