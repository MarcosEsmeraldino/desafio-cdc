package dev.desafiocdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "dev.desafiocdc.client")
@SpringBootApplication
public class DesafioCdcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioCdcApplication.class, args);
	}

}
