package com.dbteam.dbhero.models.geofox;

import java.util.Collection;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class HttpBaseEntity {
	private HashMap<String, NameValuePair> parameters;
	
	public HttpBaseEntity() {
		this.parameters = new HashMap<>();
	}
	
	public Collection<NameValuePair> toParameters() {
		return this.parameters.values();
	}
	
	protected void setParam(String key, String value) {
		if(this.parameters.containsKey(key)) {
			this.parameters.remove(key);
		} 
		
		NameValuePair pair = new BasicNameValuePair(key, value);
		this.parameters.put(key, pair);
	}
}
