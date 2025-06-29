package com.matheusdelvalle.test.testecase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TestecaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestecaseApplication.class, args);
	}

}
