package com.example.demo.models;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Game")
public class Game {

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Column(name = "game_name", unique = true, length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "console_name", length = 10)
    private Console console_name;

    @NotBlank(message = "O nome do proprietário não pode estar vazio")
    @Column(name = "owner_name", length = 50)
    private String owner_name;

    @NotBlank(message = "O número de telefone do proprietário não pode estar vazio")
    @Column(name = "owner_number", length = 15)
    private String owner_number;

    public Game(){}

    public Game(String name, Console console_name, String owner_name, String owner_number){
       this.name = name;
       this.console_name = console_name;
       this.owner_name = owner_name;
       this.owner_number = owner_number;
    }

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

    public Console getConsole_name() {
        return console_name;
    }

    public void setConsole_name(Console console_name) {
        this.console_name = console_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_number() {
        return owner_number;
    }

    public void setOwner_number(String owner_number) {
        this.owner_number = owner_number;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((console_name == null) ? 0 : console_name.hashCode());
        result = prime * result + ((owner_name == null) ? 0 : owner_name.hashCode());
        result = prime * result + ((owner_number == null) ? 0 : owner_number.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Game other = (Game) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (console_name == null) {
            if (other.console_name != null)
                return false;
        } else if (!console_name.equals(other.console_name))
            return false;
        if (owner_name == null) {
            if (other.owner_name != null)
                return false;
        } else if (!owner_name.equals(other.owner_name))
            return false;
        if (owner_number == null) {
            if (other.owner_number != null)
                return false;
        } else if (!owner_number.equals(other.owner_number))
            return false;
        return true;
    }
}
