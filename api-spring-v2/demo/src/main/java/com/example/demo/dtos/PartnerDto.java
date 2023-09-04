package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class PartnerDto {

    @NotBlank()
    private String name;

    @NotBlank()
    private String phoneNumber;

    private List<GameDto> games;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<GameDto> getGames() {
        return games;
    }

    public void setGames(List<GameDto> games) {
        this.games = games;
    }
}
