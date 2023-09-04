package com.example.demo.services;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.PartnerWithoutGameException;
import com.example.demo.models.Game;
import com.example.demo.models.Partner;
import com.example.demo.repository.PartnerRepository;

import jakarta.transaction.Transactional;

@Service
public class PartnerService {

    final PartnerRepository partnerRepository;
    final GameService gameService;

    public PartnerService(PartnerRepository partnerRepository, GameService gameService) {
        this.partnerRepository = partnerRepository;
        this.gameService = gameService;
    }

    @Transactional
    public Partner save(Partner partnerModel) throws Exception {
        if (partnerRepository.existsByNameAndPhoneNumber(partnerModel.getName(), partnerModel.getPhoneNumber())) {
            throw new AlreadyExistsException("This Partner already exists!");
        }
        if (CollectionUtils.isEmpty(partnerModel.getGames())){
            throw new PartnerWithoutGameException("To register a member it's necessary to register at least one game.");
        }
        partnerRepository.save(partnerModel);
        for (Game gameModel: partnerModel.getGames() ) {
            gameModel.setOwner(partnerModel);
            gameService.save(gameModel);
        }
        return partnerModel;
    }

    @Transactional
    public Partner update(Partner partnerModel) throws Exception {
        partnerRepository.save(partnerModel);
        return partnerModel;
    }

    @Transactional
    public void delete(Partner partnerModel) {
        partnerRepository.delete(partnerModel);
    }

    public Page<Partner> findAll(Pageable pageable) {
        return partnerRepository.findAll(pageable);
    }

    public Optional<Partner> findById(Long id) {
        return partnerRepository.findById(id);
    }

    public boolean existsByNameAndPhoneNumber(String name, String phoneNumber) {
        return partnerRepository.existsByNameAndPhoneNumber(name, phoneNumber);
    }

    public Page<Partner> findByFilter(String name, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.startsWith())
                .withIgnoreCase()
                .withIgnoreNullValues();

        Partner partnerModel = new Partner();
        partnerModel.setName(name);
        Example<Partner> example = Example.of(partnerModel, matcher);
        return partnerRepository.findAll(example, pageable);
    }
}