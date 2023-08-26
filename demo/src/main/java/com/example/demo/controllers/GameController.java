package com.example.demo.controllers;

import java.util.List;
import com.example.demo.models.Game;
import com.example.demo.services.GameService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public List<Game> allGames() {
        return gameService.getAllGames();
    }

    @PostMapping("/games")
    public Game newGame(@RequestBody Game newGame) {
        return gameService.createGame(newGame);
    }

    @GetMapping("/games/{id}")
    public Game oneGame(@PathVariable Long id) {
        return gameService.getGameById(id);
    }

    @PutMapping("/games/{id}")
    public Game updateGame(@PathVariable Long id, @RequestBody Game updGame) {
        return gameService.updateGame(id, updGame);
    }

    @DeleteMapping("/games/{id}")
    void deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
    }
}