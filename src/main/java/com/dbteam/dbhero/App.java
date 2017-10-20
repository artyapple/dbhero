package com.dbteam.dbhero;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class App extends org.telegram.telegrambots.bots.TelegramLongPollingBot {
	
	private String telegramToken;
	
	public App(BasicConfig conf){
		telegramToken = conf.telegramToken;
	}
	
	@Override
	public String getBotUsername() {
		return "dbhero_bot";
	}

	@Override
	public void onUpdateReceived(Update update) {
	    // We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	                .setChatId(update.getMessage().getChatId())
	                .setText(update.getMessage().getText());
	        try {
	            sendMessage(message); // Call method to send the message
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public String getBotToken() {
		return telegramToken;
	}

}
