package com.konasl.scoreupdate.repository;

import com.konasl.scoreupdate.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Long> {
    Score findByGuid(String guid);
    List<Score> findAllByOrderByPublishedDateDesc();
}
