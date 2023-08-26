package com.example.demo.controllers;

import java.util.List;
import com.example.demo.models.Game;
import com.example.demo.repository.GameRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameRepository repository;

    public GameController(GameRepository repository){
        this.repository = repository;
    }

    @GetMapping("/games")
    public List<Game> allEmployees(){
        return repository.findAll();
    }

    @PostMapping("/games")
    public Game newEmployee(@RequestBody Game newGame){
        return repository.save(newGame);
    }

    @GetMapping("/games/{id}")
    public Game oneEmployee(@PathVariable String id) throws Exception{
        return repository.findById(Long.parseLong(id))
        .orElseThrow(() -> new Exception("Erro"));
    }
        
    @PutMapping(value="/games/{id}")
    public Game updateEmployee (@PathVariable String id, @RequestBody Game updGame) {
        return repository.findById(Long.parseLong(id))
      .map(game -> {
        game.setName(updGame.getName());
        game.setOwner_name(updGame.getOwner_name());
        return repository.save(game);
      })
      .orElseGet(() -> {
        updGame.setId(Long.parseLong(id));
        return repository.save(updGame);
      });
    }

    @DeleteMapping("/games/{id}")
    void deleteGame(@PathVariable String id) {
        repository.deleteById(Long.parseLong(id));
    } 
}
