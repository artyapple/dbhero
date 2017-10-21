package com.dbteam.dbhero.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.dbteam.dbhero.models.geofox.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeofoxApiService {
	
	private final String uri = "http://api-hack.geofox.de/gti/public";

	public Info getInit() throws ClientProtocolException, IOException, InvalidKeyException, NoSuchAlgorithmException {
		Info result = null;
		ObjectMapper mapper = new ObjectMapper();
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(uri + "/init");
		postRequest.setEntity(new ByteArrayEntity("{}".getBytes()));		
		Header[] headers = getHeaders(EntityUtils.toByteArray(postRequest.getEntity()));
		postRequest.setHeaders(headers);

		HttpResponse httpResponse = httpclient.execute(postRequest);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			String json = EntityUtils.toString(entity);
			
			result = mapper.readValue(json, Info.class);
		}

		return result;
	}
	
	public GRResponse getRoute(GRRequest model) throws ClientProtocolException, IOException, InvalidKeyException, NoSuchAlgorithmException {
		model.setVersion(31);
		GRResponse result = null;
		ObjectMapper mapper = new ObjectMapper();
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(uri + "/getRoute");
		String jsonInString = mapper.writeValueAsString(model);
		postRequest.setEntity(new ByteArrayEntity(jsonInString.getBytes()));		
		Header[] headers = getHeaders(EntityUtils.toByteArray(postRequest.getEntity()));
		postRequest.setHeaders(headers);

		HttpResponse httpResponse = httpclient.execute(postRequest);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			String json = EntityUtils.toString(entity);
			
			result = mapper.readValue(json, GRResponse.class);
		}

		return result;
	}
	
	public String getNearstStation(float latitude, float longitude) throws ClientProtocolException, IOException, InvalidKeyException, NoSuchAlgorithmException {
		String result = "";
		ObjectMapper mapper = new ObjectMapper();
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(uri + "/checkName");
		CNRequest model = new CNRequest();
		Coordinate coordinate = new Coordinate();
		coordinate.setX((double)latitude);
		coordinate.setY((double)longitude);
		TheName theName = new TheName();
		theName.setCoordinate(coordinate);
		theName.setType("STATION");
		model.setTheName(theName);
		model.setCoordinateType("EPSG_4326");
		model.setMaxList(1);
		model.setMaxDistance(500);
		model.setVersion(31);
		String jsonInString = mapper.writeValueAsString(model);
		postRequest.setEntity(new ByteArrayEntity(jsonInString.getBytes()));		
		Header[] headers = getHeaders(EntityUtils.toByteArray(postRequest.getEntity()));
		postRequest.setHeaders(headers);

		HttpResponse httpResponse = httpclient.execute(postRequest);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			String json = EntityUtils.toString(entity);
			
			CNResponse response = mapper.readValue(json, CNResponse.class);
			List<Result> list = response.getResults();
			if(list != null && !list.isEmpty()) {
				result = list.get(0).getCombinedName();
			}
		}

		return result;
	}
	
	private Header[] getHeaders(byte[] body) throws InvalidKeyException, NoSuchAlgorithmException {
		ArrayList<Header> result = new ArrayList<>();
		result.add(new BasicHeader("Accept", "application/json"));
		result.add(new BasicHeader("geofox-auth-signature", mkSignature("#h4M3urg", body)));
		result.add(new BasicHeader("geofox-auth-user", "its-hack"));
		result.add(new BasicHeader("geofox-auth-type", "HmacSHA1"));
		result.add(new BasicHeader("Content-Type", "application/json"));
		result.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
		
		Header[] rs = new Header[result.size()];
		return result.toArray(rs);
	}

	private String mkSignature(String password, byte[] requestBody) throws NoSuchAlgorithmException, InvalidKeyException {
		final Charset passwordEncoding = Charset.forName("UTF-8");
		final String algorithm = "HmacSHA1";

		byte[] key = password.getBytes(passwordEncoding);
		SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
		Mac mac = Mac.getInstance(algorithm);
		mac.init(keySpec);
		byte[] signature = mac.doFinal(requestBody);
		return DatatypeConverter.printBase64Binary(signature);
	}
}
