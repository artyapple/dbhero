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
		GeofoxApiService apiService = new GeofoxApiService();
		try {
			GRRequest req = new  GRRequest();
			Start start =new Start();
			start.setCombinedName("Ahrensburg, Rosenhof");
			req.setStart(start);
			Destination dest = new Destination();
			dest.setCombinedName("Hamburg, Stadthausbrücke");
			req.setDest(dest);
			Time time = new Time();
			time.setDate("heute");
			time.setTime("jetzt");
			req.setTime(time);
			req.setTimeIsDeparture(true);
			req.setRealtime("REALTIME");
			GRResponse result = apiService.getRoute(req);
			ObjectMapper mapper = new ObjectMapper();
			String out = mapper.writeValueAsString(result);
			System.out.println(out);
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
		
//		BasicConfig bc = new BasicConfig();
//		
//		JCommander.newBuilder().addObject(bc).build().parse(args);
//
//		ApiContextInitializer.init();
//		TelegramBotsApi botsApi = new TelegramBotsApi();
//		
//		try {
//			botsApi.registerBot(new App(bc));
//		} catch (TelegramApiRequestException e) {
//			e.printStackTrace();
//		}

	}

}
