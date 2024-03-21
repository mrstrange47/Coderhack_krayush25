package com.crio.coderhack;

import com.crio.coderhack.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@SpringBootApplication
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class CoderhackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoderhackApplication.class, args);
		System.out.println("Working");
	}

}
//mongodb+srv://krayush:<password>@atlascluster.tmvqh32.mongodb.net/?retryWrites=true&w=majority&appName=AtlasCluster
//mongodb+srv://krayush:<password>@atlascluster.tmvqh32.mongodb.net/