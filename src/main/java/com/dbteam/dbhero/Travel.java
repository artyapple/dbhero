package com.dbteam.dbhero;

import org.telegram.telegrambots.api.objects.Location;

public class Travel {
	public String cityEnd;
	public String streetEnd;
	public Location startLocation;
	
	public void setCity(String city){
		this.cityEnd = city;
	}
	
	public void setStreet(String country){
		this.streetEnd = country;
	}
	
	public void setStartLocation(Location l){
		this.startLocation = l;
	}
}
