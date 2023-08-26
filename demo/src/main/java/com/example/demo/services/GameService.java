package com.example.demo.services;
import java.util.List;
import com.example.demo.models.Game;
import org.springframework.stereotype.Service;
import com.example.demo.repository.GameRepository;

@Service
public class GameService {

    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public List<Game> getAllGames() {
        return repository.findAll();
    }

    public Game createGame(Game newGame) {
        return repository.save(newGame);
    }

    public Game getGameById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    public Game updateGame(Long id, Game updGame) {
        return repository.findById(id)
                .map(game -> {
                    game.setName(updGame.getName());
                    game.setOwner_name(updGame.getOwner_name());
                    return repository.save(game);
                })
                .orElseGet(() -> {
                    updGame.setId(id);
                    return repository.save(updGame);
                });
    }

    public void deleteGame(Long id) {
        repository.deleteById(id);
    }
}