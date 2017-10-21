package com.dbteam.dbhero;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

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
			} else if (isProblem(content)) {
				answerMessage.setText(messageFeedback(username));
			} else if (iNeedPolice(content)) {
				answerMessage.setText(messagePolice(message));

			} else {// WRONG INPUT

			}
		} else if (message.hasLocation()) {
			Location lokation = message.getLocation();
			travel.setStartLocation(lokation);
			System.out.println(lokation);
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

	private String messageDamage() {
		return "Жду фото";
	}

	private String thankYou() {
		return "Cпасибо!";
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

	private boolean isDestination(String dest) {
		boolean res = false;
		if (dest.equalsIgnoreCase("Hamburg"))
			res = true;
		if (dest.equalsIgnoreCase("Berlin"))
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

	private boolean iNeedPolice(String prob) {
		boolean res = false;
		String dest = prob.toLowerCase();
		if (dest.contains("drunk"))
			res = true;
		return res;
	}

}
