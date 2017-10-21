package com.dbteam.dbhero;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.dbteam.dbhero.models.geofox.Destination;
import com.dbteam.dbhero.models.geofox.GRRequest;
import com.dbteam.dbhero.models.geofox.GRResponse;
import com.dbteam.dbhero.models.geofox.RealtimeSchedule;
import com.dbteam.dbhero.models.geofox.ScheduleElement;
import com.dbteam.dbhero.models.geofox.Start;
import com.dbteam.dbhero.models.geofox.Ticket;
import com.dbteam.dbhero.models.geofox.Time;
import com.dbteam.dbhero.service.GeofoxApiService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App extends org.telegram.telegrambots.bots.TelegramLongPollingBot {

	private String telegramToken;
	private Travel travel;

	public App(BasicConfig conf) {
		telegramToken = conf.telegramToken;
	}

	@Override
	public String getBotUsername() {
		return "dbhero_bot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		try {
			if (update.hasMessage()) {
				Message message = update.getMessage();
				if (message.hasText() || message.hasLocation()) {
					handleIncomingMessage(message);
				}
			}

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotToken() {
		return telegramToken;
	}

	@Autowired
	private void handleIncomingMessage(Message message) throws TelegramApiException {

		System.out.println("bla");
		String username = message.getFrom().getFirstName();
		SendMessage answerMessage = new SendMessage().setChatId(message.getChatId());

		if (message.hasText()) {
			String content = message.getText();

			if (content.equalsIgnoreCase("/start")) {
				answerMessage.setText(messageDefault(username));
			} else if (isDestination(content)) {
				String[] dest = content.replaceAll(" ", "").split(",");				
				travel = new Travel();
				travel.setStreet(dest[1]);
				travel.setCity(dest[0]);
				answerMessage.setText(messageWhereIsStart());
			} else if (isProblem(content)) {
				answerMessage.setText(messageFeedback(username));
			} else if (iNeedPolice(content)) {
				answerMessage.setText(messagePolice(message));
			} else if (isReading(content)){
				Long chatId = message.getChatId();
				String caption = "The Old Man and the Sea - Ernest Hemingway";
				java.io.File bookf = new File("C:\\Users\\AI\\Desktop\\Hemmingway_The Old Man and the Sea_1952.pdf");
				sendDocUploadingAFile(chatId, bookf, caption);
				answerMessage.setText(getBook());
			} else if (isGoodTravel(content)){
				answerMessage.setText(totalTravelingTime());
			} else {
				answerMessage.setText("");
			}
		} else if (message.hasLocation()) {
			Location lokation = message.getLocation();
			travel.setStartLocation(lokation);
			answerMessage.setText(buildRouteGeoFox(travel));
			System.out.println(lokation.getLatitude()+ " ::: " +lokation.getLongitude());
		} else {
			
		}

		sendMessage(answerMessage); // Call method to send the message

	}

	private String messageDefault(String name) {
		return "Hi, "+ name +", welcome on board! I’m Captain Howl, well known travel buddy for Hamburg area. Where are we going today?";
	}

	private String messageFeedback(String name) {
		return "Oh... Noted! I’ve told me colleagues, they’ll manage that asap! Thank you for informing, " + name + "!";
	}

	private String messagePolice(Message old){
		SendMessage fMessage = new SendMessage().setChatId(old.getChatId());
		fMessage.setText("Keep clam. Police is coming!");
		try {
			sendMessage(fMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
		return "Keep distance from this person and don’t pay attention to him. Would you like me to share your phone number with the nearest policeman?";
	}

	private boolean isDestination(String desti) {
		boolean res = false;
		String dest = desti.toLowerCase();
		if (dest.contains("hamburg"))
			res = true;
		if (dest.contains("berlin"))
			res = true;
		return res;
	}

	private boolean isProblem(String prob) {
		boolean res = false;
		String dest = prob.toLowerCase();
		if (dest.contains("broken"))
			res = true;
		if (dest.contains("fallen"))
			res = true;
		return res;
	}
	
	private boolean isReading(String prob) {
		boolean res = false;
		String dest = prob.toLowerCase();
		if (dest.contains("reading"))
			res = true;
		return res;
	}
	
	private boolean isGoodTravel(String prob){
		boolean res = false;
		String dest = prob.toLowerCase();
		if (dest.contains("good"))
			res = true;
		return res;
	}

	private boolean iNeedPolice(String prob) {
		boolean res = false;
		String dest = prob.toLowerCase();
		if (dest.contains("drunk"))
			res = true;
		return res;
	}
	
	private String buildRouteGeoFox(Travel travel){
		GeofoxApiService apiService = new GeofoxApiService();
		
		try {
			GRRequest req = new  GRRequest();
			Start start =new Start();
			String startAddr = apiService.getNearstStation(travel.startLocation.getLongitude(),travel.startLocation.getLatitude());
			start.setCombinedName(startAddr);
			req.setStart(start);
			Destination dest = new Destination();
			dest.setCombinedName(travel.cityEnd +" , "+ travel.streetEnd);
			req.setDest(dest);
			Time time = new Time();
			time.setDate("heute");
			time.setTime("jetzt");
			req.setTime(time);
			req.setTimeIsDeparture(true);
			req.setRealtime("REALTIME");
			GRResponse result = apiService.getRoute(req);
			String out = getFormattedResponse(result);
			//ObjectMapper mapper = new ObjectMapper();
			//String out = mapper.writeValueAsString(result);
			return out;
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
		return "no data";
	}
	
	private String getFormattedResponse(GRResponse obj) {
		StringBuilder response = new StringBuilder();
		response.append("We have the best traffic route for you and I will support you on the way.");
		response.append("\n");
		RealtimeSchedule schedule = obj.getRealtimeSchedules().get(0);
		for(ScheduleElement element : schedule.getScheduleElements()){
			String startName = element.getFrom().getCombinedName();
			String startTime = element.getFrom().getDepTime().getTime(); 
			response.append("From: "+startName + " at "+startTime);
			response.append("\n");
			String line = element.getLine().getName();
			String origin = element.getLine().getOrigin();
			String direction = element.getLine().getDirection();
			if(!line.equals("Umstiegsfußweg")){
				response.append("Line: "+line);
				response.append("\n");
				response.append("Direction: "+  direction);
			} else {
				response.append("Walking");
			}
			response.append("\n");
			String endName = element.getTo().getCombinedName();
			String endTime = element.getTo().getArrTime().getTime();
			response.append("To: "+endName + " at "+endTime);
			response.append("\n");
			response.append("-------------------------------");
			response.append("\n");
		}
		Ticket ticket = schedule.getTickets().get(0);
		response.append(ticket.getType() +" " + ticket.getPrice());
		response.append("\n");
		response.append("\n");
		response.append("[Ticket](https://shop.hvv.de/index.php/product/40/show/0/0/0/0)");
		 		
		return response.toString();
	}

	private void sendDocUploadingAFile(Long chatId, java.io.File save,String caption) throws TelegramApiException {

	    SendDocument sendDocumentRequest = new SendDocument();
	    sendDocumentRequest.setChatId(chatId);
	    sendDocumentRequest.setNewDocument(save);
	    sendDocumentRequest.setCaption(caption);
	    sendDocument(sendDocumentRequest);
	}
	
	private String messageWhereIsStart(){
		return "Where should we start?";
	}
	
	private String totalTravelingTime(){
		return "The total traveling time is around 45 minutes, hopefully Deutsche Bahn will not be late for 74 sec this time 😉  Wanna have some entertainment? ";
	}
	
	private String getBook(){
		return "The Old man and the fish is nice choice for the short trip. Have fun!";
	} 

}
