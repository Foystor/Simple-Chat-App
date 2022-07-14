package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
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
	private String baseURL;

	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void afterAll() {
		driver.quit();
		driver = null;
	}

	@BeforeEach
	public void beforeEach() {
		baseURL = "http://localhost:" + port;
	}

	void signupUser(String username, String password) {
		driver.get(baseURL + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("first","last", username, password);
	}

	void loginUser(String username, String password) {
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
	}

	@Test
	void singleUser_signupAndLoginAndChat_usernameAndMsgCorrect() {
		String username = "Foy";
		String password = "password";
		String msg = "Hello";
		String msgType = "Shout";

		signupUser(username, password);
		loginUser(username, password);
		ChatPage chatPage = new ChatPage(driver);
		chatPage.sendChatMessage(msg, msgType);

		// verify
		ChatMessage firstMsg = chatPage.getMessages().get(0);
		assertEquals(username, firstMsg.getUsername());
		assertEquals(msg.toUpperCase(), firstMsg.getMessageText());
	}

	@Test
	void twoUsers_signupAndLoginAndChat_displaySameMsgs() {
		String username1 = "Foy";
		String username2 = "Cruella";
		String password = "password";
		String msg1 = "Yahoo!";
		String msgType1 = "Shout";
		String msg2 = "Aha";
		String msgType2 = "Whisper";

		// user1
		signupUser(username1,password);
		loginUser(username1,password);
		ChatPage chatPage = new ChatPage(driver);
		chatPage.sendChatMessage(msg1, msgType1);
		chatPage.logout();

		// user2
		signupUser(username2, password);
		loginUser(username2, password);
		chatPage = new ChatPage(driver);
		chatPage.sendChatMessage(msg2, msgType2);

		// get messages
		List<ChatMessage> messages2 = chatPage.getMessages();
		chatPage.logout();
		loginUser(username1, password);
		List<ChatMessage> messages1 = chatPage.getMessages();

		// verify
		for (int i = 0; i < messages1.size(); i++) {
			assertEquals(messages1.get(i).getUsername(), messages2.get(i).getUsername());
			assertEquals(messages1.get(i).getMessageText(), messages2.get(i).getMessageText());
		}
	}
}
