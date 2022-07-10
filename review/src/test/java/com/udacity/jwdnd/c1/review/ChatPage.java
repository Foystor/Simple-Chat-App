package com.udacity.jwdnd.c1.review;

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

    @FindBy(id = "submit")
    private WebElement submitBtn;

    @FindBy(className = "username")
    private List<WebElement> usernameDisplay;

    @FindBy(className = "message")
    private List<WebElement> msgDisplay;

    @FindBy(id = "logout_link")
    private WebElement logoutLink;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void inputMessage(String msg) {
        messageTextField.clear();
        messageTextField.sendKeys(msg);
    }

    public void selectMessageType(String type) {
        Select select = new Select(messageTypeSelect);
        select.selectByValue(type);
    }

    public void submitMsg() {
        submitBtn.click();
    }

    public List<String> getDisplayedUsername() {
        List<String> displayedUsername = new ArrayList<>();
        for (WebElement nameDisplay : usernameDisplay) {
            displayedUsername.add(nameDisplay.getText());
        }
        return displayedUsername;
    }

    public List<String> getDisplayedMsg() {
        List<String> displayedMsg = new ArrayList<>();
        for (WebElement msgDisplay : msgDisplay) {
            displayedMsg.add(msgDisplay.getText());
        }
        return displayedMsg;
    }

    public void logout() {
        logoutLink.click();
    }
}
