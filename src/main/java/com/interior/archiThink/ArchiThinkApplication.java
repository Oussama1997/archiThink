package com.interior.archiThink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.interior.archiThink")
public class ArchiThinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchiThinkApplication.class, args);
	}

}
