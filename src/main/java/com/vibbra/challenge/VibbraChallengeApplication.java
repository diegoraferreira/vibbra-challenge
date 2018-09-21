package com.vibbra.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.vibbra.challenge.repository")
@ComponentScan("com.vibbra.challenge")
public class VibbraChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(VibbraChallengeApplication.class, args);
	}
}
