package com.dbteam.dbhero;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import com.beust.jcommander.JCommander;
import com.dbteam.dbhero.models.geofox.*;
import com.dbteam.dbhero.models.geofox.Info;
import com.dbteam.dbhero.service.GeofoxApiService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class DbheroApplication {

	public static void main(String[] args) {

		SpringApplication.run(DbheroApplication.class, args);
				
		BasicConfig bc = new BasicConfig();
		
		JCommander.newBuilder().addObject(bc).build().parse(args);

		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		
		try {
			botsApi.registerBot(new App(bc));
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}

	}

}
