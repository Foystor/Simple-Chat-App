package com.udacity.jwdnd.c1.review;

public class ChatForm {

    private String username;
    private String messageText;
    private MessageType messageType;

    public String getUsername() {
        return username;
    }

    public String getMessageText() {
        return messageText;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
