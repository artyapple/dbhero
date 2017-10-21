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

		String username = message.getFrom().getFirstName();
		SendMessage answerMessage = new SendMessage() // Create a SendMessage
														// object with mandatory
														// fields
				.setChatId(message.getChatId());

		if (message.hasLocation()) {
			Location lokation = message.getLocation();
			travel.setStartLocation(lokation);
			System.out.println(lokation);			
		} else if (message.hasPhoto()) {
			System.out.println("thank you!");
			answerMessage.setText(thankYou());
		} else if (message.hasText()) {
			String content = message.getText();
					
			if (content.equalsIgnoreCase("/start")) {
				answerMessage.setText(messageDefault(username));
			} else if (isDestination(content)) {
				String[] dest = content.replaceAll(" ", "").split(",");
				travel = new Travel();
				travel.setStreet(dest[1]);
				travel.setCity(dest[0]);
			} else if (content.toLowerCase().contains("feedback")) {
				answerMessage.setText(messageFeedback(username));
			} else if (content.toLowerCase().contains("damage")) {
				answerMessage.setText(messageDamage());
			} else {// WRONG INPUT

			}
		} else {

		}

		sendMessage(answerMessage); // Call method to send the message

	}

	private String messageDefault(String name) {
		return "Привет, " + name + "!" + "Как дела? Куда поедем?";
	}

	private String messageFeedback(String name) {
		return name + ", что то случилось? Хочешь оставить отзыв или увидел какую-то паломку?";
	}

	private String messageDamage() {
		return "Жду фото";
	}

	private String thankYou() {
		return "Cпасибо!";
	}

	private boolean isDestination(String dest) {
		boolean res = false;
		if (dest.equalsIgnoreCase("Hamburg"))
			res = true;
		if (dest.equalsIgnoreCase("Berlin"))
			res = true;
		return res;
	}

}
