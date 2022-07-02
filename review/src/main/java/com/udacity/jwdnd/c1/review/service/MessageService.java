package com.udacity.jwdnd.c1.review.service;


import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<ChatMessage> messageList;

//    private String message;
//
//    public MessageService(String message) {
//        this.message = message;
//    }
//
//    public String uppercase() {
//        return message.toUpperCase();
//    }
//
//    public String lowercase() {
//        return message.toLowerCase();
//    }

    public void addMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say" -> newMessage.setText(chatForm.getMessageText());
            case "Shout" -> newMessage.setText(chatForm.getMessageText().toUpperCase());
            case "Whisper" -> newMessage.setText(chatForm.getMessageText().toLowerCase());
        }

        this.messageList.add(newMessage);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating MessageService bean");
        messageList = new ArrayList<>();
    }

    public List<ChatMessage> getMessageList() {
        return messageList;
    }
}
