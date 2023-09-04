package com.example.demo.models;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(
        name = "TB_GAME", uniqueConstraints = {@UniqueConstraint(name = "TB_GAME_UQ", columnNames = {"name", "console", "partner"})}
)
@Data
@NoArgsConstructor
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Console console;

    @JsonIgnoreProperties("games")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "partner", foreignKey = @ForeignKey(name = "FK_PARTNER"), nullable = false)
    private Partner owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public Partner getOwner() {
        return owner;
    }

    public void setOwner(Partner owner) {
        this.owner = owner;
    }

    
}