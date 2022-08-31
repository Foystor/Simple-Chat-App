# Simple Chat Application

A **practice project** from the **Spring Boot Basics** course in [Java Web Developer Nanodegree](https://www.udacity.com/course/java-developer-nanodegree--nd035) at Udacity.

[_See the final project of this course._](https://github.com/Foystor/SuperDuperDrive)

A rudimentary chat application where users can submit messages along with their username, and those messages (along with the usernames of the users who sent them) will be visible to any user that navigates to the `/chat` URL.

Additionally, users should be able to select a message "mode": either `Say`, `Shout`, or `Whisper`. `Say` should display the message as written, while `Shout` should display the message in all-uppercase, and `Whisper` should display the message in all-lowercase.

## Demo

https://user-images.githubusercontent.com/74621252/187640198-c91f409e-e048-4e47-b5a8-499fbb77623f.mov

## Technologies

- Spring Boot
- Spring MVC
- Thymeleaf
- MyBatis
- H2 in-memory database
- Spring Security
- Junit
- Selenium
- Bootstrap
- SQL

## Usage

1. In the `root` directory:

```
$ git clone https://github.com/Foystor/Simple-Chat-App.git
```

2. Get to the `review` directory:

```
$ cd Simple-Chat-App/review
```

3. Build the project skip tests:

```
$ mvn clean package -Dmaven.test.skip
```

4. Run the application:

```
$ java -jar target/review-0.0.1-SNAPSHOT.jar
```

5. Navigate to http://localhost:8080/login and start chatting.

## License

[MIT License](LICENSE)
