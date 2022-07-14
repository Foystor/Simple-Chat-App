package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ChatPage {
    @FindBy(id = "messageText")
    private WebElement messageTextField;

    @FindBy(id = "messageType")
    private WebElement messageTypeSelect;

    @FindBy(id = "submit_btn")
    private WebElement submitBtn;

    @FindBy(className = "chatMessageUsername")
    private List<WebElement> usernameDisplay;

    @FindBy(className = "chatMessageText")
    private List<WebElement> msgDisplay;

    @FindBy(id = "logout_link")
    private WebElement logoutLink;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void sendChatMessage(String msg, String msgType) {
        messageTextField.sendKeys(msg);
        Select select = new Select(messageTypeSelect);
        select.selectByValue(msgType);
        submitBtn.click();
    }

    public List<ChatMessage> getMessages() {
        List<ChatMessage> results = new ArrayList<>();
        for (int i = 0; i < usernameDisplay.size(); i++) {
            ChatMessage result = new ChatMessage();
            result.setUsername(usernameDisplay.get(i).getText());
            result.setMessageText(msgDisplay.get(i).getText());
            results.add(result);
        }
        return results;
    }

    public void logout() {
        logoutLink.click();
    }
}
