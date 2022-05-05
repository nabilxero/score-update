package com.konasl.scoreupdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScoreUpdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoreUpdateApplication.class, args);
	}

}
