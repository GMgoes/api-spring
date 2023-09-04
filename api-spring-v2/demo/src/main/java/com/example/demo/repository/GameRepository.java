package com.example.demo.repository;

import com.example.demo.models.Console;
import com.example.demo.models.Game;
import com.example.demo.models.Partner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long>{

    boolean existsByNameAndConsoleAndOwner(String name, Console consoleEnum, Partner owner);
}