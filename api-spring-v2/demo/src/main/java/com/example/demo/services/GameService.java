package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dtos.GameDto;
import com.example.demo.models.Console;
import com.example.demo.models.Game;
import com.example.demo.models.Partner;
import com.example.demo.repository.GameRepository;
import com.example.demo.repository.PartnerRepository;

import jakarta.transaction.Transactional;

@Service
public class GameService {
final GameRepository gameRepository;
    final PartnerRepository partnerRepository;


    public GameService(GameRepository gameRepository, PartnerRepository partnerRepository) {
        this.gameRepository = gameRepository;
        this.partnerRepository = partnerRepository;
    }

    @Transactional
    public Game save(Game gameModel) {
        if (gameRepository.existsByNameAndConsoleAndOwner(gameModel.getName(), gameModel.getConsole(), gameModel.getOwner())) {
            throw new ResponseStatusException( HttpStatus.CONFLICT, "This game already exists!");
        }
       return gameRepository.save(gameModel);
    }

    @Transactional
    public Game update(Game gameModel) {

        return gameRepository.save(gameModel);
    }
    @Transactional
    public void delete(Game gameModel) {
        gameRepository.delete(gameModel);
    }

    public Page<Game> findAll(Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    public boolean existsByNameAndConsoleAndOwner(String name, Console consoleEnum, Partner owner) {
        return gameRepository.existsByNameAndConsoleAndOwner(name, consoleEnum, owner);
    }
    
    public Page<Game> findByConsoleAndFilter(GameDto gameDto, Pageable pageable) {
        var gameModel = new Game();
        BeanUtils.copyProperties(gameDto, gameModel);

        var matcher = ExampleMatcher.matching()
                .withMatcher("console", match -> match.exact())
                .withMatcher("name", match -> match.startsWith())
                .withMatcher("owner.name", match -> match.contains())
                .withIgnoreCase()
                .withIgnoreNullValues();

        var example = Example.of(gameModel, matcher);

        return gameRepository.findAll(example, pageable);
    }

    public Page<Game> findByOwnerId(Long ownerId, Pageable pageable) {
        var matcher = ExampleMatcher.matching()
                .withMatcher("owner.id", match -> match.exact())
                .withIgnoreCase()
                .withIgnoreNullValues();

        var gameModel = new Game();

        var partnerModel = new Partner();
        partnerModel.setId(ownerId);

        gameModel.setOwner(partnerModel);

        var example = Example.of(gameModel, matcher);

        return gameRepository.findAll(example, pageable);
    }
}