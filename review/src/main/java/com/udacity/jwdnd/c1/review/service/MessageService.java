package com.udacity.jwdnd.c1.review.service;


import com.udacity.jwdnd.c1.review.mapper.MessageMapper;
import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

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
            case "Say" -> newMessage.setMessageText(chatForm.getMessageText());
            case "Shout" -> newMessage.setMessageText(chatForm.getMessageText().toUpperCase());
            case "Whisper" -> newMessage.setMessageText(chatForm.getMessageText().toLowerCase());
        }

        this.messageMapper.insert(newMessage);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating MessageService bean");
    }

    public List<ChatMessage> getMessageList() {
        return this.messageMapper.getMessages();
    }
}
