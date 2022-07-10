package com.udacity.jwdnd.c1.review;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewApplicationTests {
	@LocalServerPort
	private Integer port;

	private static WebDriver driver;
	private LoginPage loginPage;
	private SignupPage signupPage;
	private ChatPage chatPage;

	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void afterAll() {
		driver.quit();
	}

	@BeforeEach
	public void beforeEach() {
		driver.get("http://localhost:" + port + "/login");
		loginPage = new LoginPage(driver);
		signupPage = new SignupPage(driver);
		chatPage = new ChatPage(driver);
	}

	void signupUser(String username) {
		String firstname = "firstname";
		String lastname = "lastname";
		String password = "1";

		driver.get("http://localhost:" + port + "/signup");
		signupPage.inputFirstname(firstname);
		signupPage.inputLastname(lastname);
		signupPage.inputUsername(username);
		signupPage.inputPassword(password);
		signupPage.signup();
	}

	void loginUser(String username) {
		String password = "1";

		driver.get("http://localhost:" + port + "/login");
		loginPage.inputUsername(username);
		loginPage.inputPassword(password);
		loginPage.login();
	}

	@Test
	void singleUser_signupAndLoginAndChat_usernameAndMsgCorrect() {
		String username = "Foy";
		String msg = "Hello";
		String msgType = "Shout";

		signupUser(username);
		loginUser(username);

		// chat
		chatPage.inputMessage(msg);
		chatPage.selectMessageType(msgType);
		chatPage.submitMsg();

		// verify
		assertEquals(username,chatPage.getDisplayedUsername().get(0));
		assertEquals(msg.toUpperCase(),chatPage.getDisplayedMsg().get(0));
	}

	@Test
	void twoUsers_signupAndLoginAndChat_displaySameMsgs() {
		String username1 = "Foy";
		String msg1 = "Yahoo!";
		String msgType1 = "Shout";
		String username2 = "Cruella";
		String msg2 = "Aha";
		String msgType2 = "Whisper";

		signupUser(username1);
		loginUser(username1);
		chatPage.inputMessage(msg1);
		chatPage.selectMessageType(msgType1);
		chatPage.logout();

		signupUser(username2);
		loginUser(username2);
		chatPage.inputMessage(msg2);
		chatPage.selectMessageType(msgType2);
		// get messages
		List<String> usernames2 = chatPage.getDisplayedUsername();
		List<String> msgs2 = chatPage.getDisplayedMsg();
		chatPage.logout();

		loginUser(username1);
		// get messages
		List<String> usernames1 = chatPage.getDisplayedUsername();
		List<String> msgs1 = chatPage.getDisplayedMsg();

		// verify
		assertIterableEquals(usernames1,usernames2);
		assertIterableEquals(msgs1,msgs2);
	}
}
