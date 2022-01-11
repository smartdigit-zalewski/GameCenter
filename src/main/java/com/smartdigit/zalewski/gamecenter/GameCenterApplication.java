package com.smartdigit.zalewski.gamecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class}
)
public class GameCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameCenterApplication.class, args);
	}

}
