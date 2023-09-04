package com.example.demo.services;

import com.example.demo.models.Game;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.repository.GameRepository;

@Service
public class GameService {

    private final GameRepository repository_two;

    public GameService(GameRepository repository) {
        this.repository_two = repository;
    }

    public Page<Game> getAllGames(Pageable pageable) {
        return repository_two.findAll(pageable);
    }

    public Game createGame(Game newGame) {
        return repository_two.save(newGame);
    }

    public Game getGameById(Long id) {
        return repository_two.findById(id)
                .orElseThrow();
    }

    public Page<Game> getGameByFilter(Game game_example, Pageable pageable){
        var game_model = new Game();
        BeanUtils.copyProperties(game_example,game_model);

        var matcher = ExampleMatcher.matching()
        .withMatcher("console_name", match -> match.exact())
        .withMatcher("game_name", match -> match.startsWith())
        .withMatcher("owner_name", match -> match.contains())
        .withIgnoreCase()
        .withIgnoreNullValues();

        var example = Example.of(game_model, matcher);
        return repository_two.findAll(example, pageable);
    }

    public Game updateGame(Long id, Game updGame) {
        return repository_two.findById(id)
                .map(game -> {
                    game.setName(updGame.getName());
                    return repository_two.save(game);
                })
                .orElseGet(() -> {
                    updGame.setId(id);
                    return repository_two.save(updGame);
                });
    }

    public String deleteGame(Long id) {
        try{
            repository_two.deleteById(id);
            return "Deleted Game";
        } catch (Exception e){
            return e.getMessage();
        }
    }
}