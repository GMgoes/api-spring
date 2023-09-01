package com.example.demo.controllers;

import com.example.demo.models.Console;
import com.example.demo.models.Game;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import com.example.demo.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping()
    public ResponseEntity<Page<Game>> allGames(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getAllGames(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> oneGame(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getGameById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Game>> findGameByConsoleAndFilters(
        @RequestParam(required = false) String console_name,
        @RequestParam(required = false) String game_name,
        @RequestParam(required = false) String owner_name,
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable){

            var game_model = new Game();
            Console console_of_enum = Console.functionGetValue(console_name);
            game_model.setConsole_name(console_of_enum);
            game_model.setName(game_name);
            game_model.setOwner_name(owner_name);
            System.out.println(game_model.toString());

            return ResponseEntity.status(HttpStatus.OK).body(gameService.getGameByFilter(game_model, pageable));
        }

    @PostMapping()
    public ResponseEntity<Game> newGame(@RequestBody Game newGame) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.createGame(newGame));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game updGame) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.updateGame(id, updGame));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.deleteGame(id));
    }
}