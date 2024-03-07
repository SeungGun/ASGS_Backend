package com.asgs.allimi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AllimiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllimiApplication.class, args);
	}

}
