package com.udacity.jwdnd.c1.review;


import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<ChatMessage> messageList;
    private String message;

    public MessageService(String message) {
        this.message = message;
    }

    public String uppercase() {
        return message.toUpperCase();
    }

    public String lowercase() {
        return message.toLowerCase();
    }

    public void addMessage(String username, String message, MessageType messageType) {
        this.message = message;
        switch (messageType) {
            case SHOUT -> this.message = uppercase();
            case WHISPER -> this.message = lowercase();
        }
        this.messageList.add(new ChatMessage(username, this.message));
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
