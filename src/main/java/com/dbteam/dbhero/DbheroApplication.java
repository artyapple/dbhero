package com.dbteam.dbhero;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dbteam.dbhero.models.geofox.Info;
import com.dbteam.dbhero.service.GeofoxApiService;

@SpringBootApplication
public class DbheroApplication {

	public static void main(String[] args) {
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
		SpringApplication.run(DbheroApplication.class, args);
	}
}
