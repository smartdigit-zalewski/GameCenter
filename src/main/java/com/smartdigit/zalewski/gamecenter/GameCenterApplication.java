package com.smartdigit.zalewski.gamecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

@SpringBootApplication
public class GameCenterApplication {

	public static void main(String[] args) {

		SpringApplication.run(GameCenterApplication.class, args);


	}

}
