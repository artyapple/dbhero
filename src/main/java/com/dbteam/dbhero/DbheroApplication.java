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

import com.dbteam.dbhero.models.geofox.Info;
import com.dbteam.dbhero.service.GeofoxApiService;

@SpringBootApplication
public class DbheroApplication {

	public static void main(String[] args) {


		SpringApplication.run(DbheroApplication.class, args);
		GeofoxApiService apiService = new GeofoxApiService();
		try {
			Info result = apiService.getInit();
			System.out.println(result.getBuildText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
