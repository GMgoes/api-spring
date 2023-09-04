package com.example.demo.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dtos.GameDto;
import com.example.demo.models.Console;
import com.example.demo.models.Game;
import com.example.demo.models.Partner;

public class GameClubFacade {
    final GameService gameService;
    final PartnerService partnerService;

    public GameClubFacade(GameService gameService, PartnerService partnerService) {
        this.gameService = gameService;
        this.partnerService = partnerService;
    }

    public boolean gameExistsByNameAndConsoleAndOwner(String name, Console console, Partner owner) {
        return gameService.existsByNameAndConsoleAndOwner(name, console, owner);
    }

    public Game gameSave(Game gameModel) {
        return gameService.save(gameModel);
    }

    public Game gameUpdate(Game gameModel) {
        return gameService.update(gameModel);
    }

    public Optional<Game> gameFindById(Long id) {
        return gameService.findById(id);
    }

    public void gameDelete(Game gameModel) {
        var partner = partnerService.findById(gameModel.getOwner().getId());
        if (partner.isPresent()) {
            if( partner.get().getGames().size() > 1){
                gameService.delete(gameModel);
            }
            else {
                partnerService.delete(partner.get());
            }
        }
    }

    public Page<Game> gameFindAll(Pageable pageable) {
        return gameService.findAll(pageable);
    }

    public Page<Game> gameFindByConsoleAndFilter(GameDto gameDto, Pageable pageable) {
        return gameService.findByConsoleAndFilter(gameDto, pageable);
    }

    public Page<Game> gameFindByOwnerId(Long ownerId, Pageable pageable) {
        return gameService.findByOwnerId(ownerId, pageable);
    }

    public Partner partnerSave(Partner partnerModel) throws Exception {
        return partnerService.save(partnerModel);
    }

    public Partner partnerUpdate(Partner partnerModel) throws Exception {
        return partnerService.update(partnerModel);
    }

    public Optional<Partner> partnerFindById(Long id) {
        return partnerService.findById(id);
    }

    public void partnerDelete(Partner partnerModel) {
        partnerService.delete(partnerModel);

    }

    public Page<Partner> partnerFindAll(Pageable pageable) {
        return partnerService.findAll(pageable);
    }

    public Page<Partner> partnerFindByFilter(String name, Pageable pageable) {
        return partnerService.findByFilter(name,pageable);
    }
}
