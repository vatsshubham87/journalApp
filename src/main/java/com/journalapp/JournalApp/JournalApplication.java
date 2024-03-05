package com.journalapp.JournalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
	ConfigurableEnvironment environment =  context.getEnvironment();
//		System.out.println(Arrays.toString(environment.getActiveProfiles()));
	}

	@Bean
	public PlatformTransactionManager transactionManager(MongoDatabaseFactory databaseFactory)
	{
		return new MongoTransactionManager(databaseFactory);
	}

}
