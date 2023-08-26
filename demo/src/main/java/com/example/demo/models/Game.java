package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Game")
public class Game {

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Column(name = "name", unique = true, length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    private Console console_name;
    @NotBlank(message = "O nome do proprietário não pode estar vazio")
    @Column(name = "owner_name", unique = true, length = 50)
    private String owner_name;

    public Game(){}

    public Game(String name, Console console_name, String owner_name){
       this.name = name;
       this.console_name = console_name;
       this.owner_name = owner_name;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((console_name == null) ? 0 : console_name.hashCode());
        result = prime * result + ((owner_name == null) ? 0 : owner_name.hashCode());
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
        if (console_name != other.console_name)
            return false;
        if (owner_name == null) {
            if (other.owner_name != null)
                return false;
        } else if (!owner_name.equals(other.owner_name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", name=" + name + ", console_name=" + console_name + ", owner_name=" + owner_name
                + "]";
    }
}
