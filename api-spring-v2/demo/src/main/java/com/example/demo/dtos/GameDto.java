package com.example.demo.dtos;

import com.example.demo.models.Console;
import com.example.demo.models.Partner;

import jakarta.validation.constraints.NotBlank;

public class GameDto {

    @NotBlank()
    private String name;

    private Console console;

    private Partner owner;

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
