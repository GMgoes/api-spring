package com.example.demo.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.GameDto;
import com.example.demo.models.Console;
import com.example.demo.models.Game;
import com.example.demo.models.Partner;
import com.example.demo.services.GameService;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {
final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Object> saveGame(@RequestBody @Valid GameDto gameDto) {
        if (gameService.existsByNameAndConsoleAndOwner(gameDto.getName(), gameDto.getConsole(), gameDto.getOwner())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This game already exists!");
        }

        var gameModel = new Game();
        BeanUtils.copyProperties(gameDto, gameModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(gameModel));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getGameById(@PathVariable(value = "id") Long id) {
        Optional<Game> gameModelOptional = gameService.findById(id);
        if (!gameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGame(@PathVariable(value = "id") Long id) {
        Optional<Game> gameModelOptional = gameService.findById(id);
        if (!gameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found.");
        }
        gameService.delete(gameModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Game deleted successfully.");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable(value = "id") Long id,
                                             @RequestBody @Valid GameDto gameDto) {
        Optional<Game> gameModelOptional = gameService.findById(id);
        if (!gameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found.");
        }
        var gameModel = new Game();
        BeanUtils.copyProperties(gameDto, gameModel);
        gameModel.setId(gameModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(gameService.save(gameModel));
    }


    @GetMapping
    public ResponseEntity<Page<Game>> getAllGames(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.findAll(pageable));
    }

    @GetMapping("/search/{console}")
    public ResponseEntity<Page<Game>> findGamesByConsoleAndFilters(@PathVariable Console console,
                                                                        @RequestParam(required = false) String name,
                                                                        @RequestParam(required = false) Partner owner,
                                                                        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        var gameDto = new GameDto();
        gameDto.setConsole(console);
        gameDto.setName(name);
        gameDto.setOwner(owner);

        return ResponseEntity.status(HttpStatus.OK).body(gameService.findByConsoleAndFilter(gameDto, pageable));
    }
}