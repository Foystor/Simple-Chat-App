package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement firstnameField;

    @FindBy(id = "inputLastName")
    private WebElement lastnameField;

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement signupBtn;

    @FindBy(id = "login_link")
    private WebElement loginLink;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void inputFirstname(String firstname) {
        firstnameField.clear();
        firstnameField.sendKeys(firstname);
    }

    public void inputLastname(String lastname) {
        lastnameField.clear();
        lastnameField.sendKeys(lastname);
    }

    public void inputUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void inputPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void signup() {
        signupBtn.click();
    }

    public void toLogin() {
        loginLink.click();
    }
}
