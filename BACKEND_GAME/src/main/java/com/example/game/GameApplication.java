package com.example.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//The @SpringBootApplication annotation indicates that this is the main class for the Spring Boot application.
//        The @Autowired annotation is used to inject an instance of GameManager into the Application class. Spring automatically provides the GameManager bean when the application starts.

@SpringBootApplication
public class GameApplication implements CommandLineRunner {

    private final GameManager gameManager;

    @Autowired
    public GameApplication(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        gameManager.startGame();
    }
}
