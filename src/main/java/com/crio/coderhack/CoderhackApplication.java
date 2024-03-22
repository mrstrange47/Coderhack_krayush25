package com.crio.coderhack;

import com.crio.coderhack.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class CoderhackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoderhackApplication.class, args);
		System.out.println("Server Started Successfully");
	}

}
