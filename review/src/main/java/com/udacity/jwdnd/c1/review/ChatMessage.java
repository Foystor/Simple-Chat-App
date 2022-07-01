package com.udacity.jwdnd.c1.review;

public class ChatMessage {

    private String username;
    private String text;

    public ChatMessage(String username, String text) {
        this.username = username;
        this.text = text;
    }

    @Override
    public String toString() {
        return  username + ": " + text;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setText(String text) {
        this.text = text;
    }
}
