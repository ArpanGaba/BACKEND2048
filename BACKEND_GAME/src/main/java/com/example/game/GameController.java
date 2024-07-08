package com.example.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameManager gameManager;

    @Autowired
    public GameController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @GetMapping("/board")
    public int[][] getBoard() {
        return gameManager.getBoard();
    }

    @PostMapping("/move")
    public int[][] move(@RequestParam String direction) {   //the @RequestParam annotation is used to extract query parameters from the URL in a web request
        boolean moved;
        switch (direction.toLowerCase()) {
            case "up":
                moved = gameManager.moveUp();
                break;
            case "down":
                moved = gameManager.moveDown();
                break;
            case "left":
                moved = gameManager.moveLeft();
                break;
            case "right":
                moved = gameManager.moveRight();
                break;
            default:
                throw new IllegalArgumentException("Invalid move direction: " + direction);
        }
        if (moved) {
            gameManager.addTile();
        }
        return gameManager.getBoard();
    }
}
