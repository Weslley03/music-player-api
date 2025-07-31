package com.weftecnologia.music_player_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.weftecnologia.music_player_api.config.EnvVariables;

@SpringBootApplication
public class MusicPlayerApiApplication {

	public static void main(String[] args) {
		System.setProperty("MONGODB_URI", EnvVariables.getMongoUri());
		SpringApplication.run(MusicPlayerApiApplication.class, args);
	}

}
