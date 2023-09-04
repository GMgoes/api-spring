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

    @NotBlank(message = "Name field cannot be empty")
    @Column(name = "name", unique = true, length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "console",unique = true, length = 10)
    private Console console;

    @NotBlank(message = "Owner name field cannot be empty")
    @Column(name = "owner",unique = true, length = 50)
    private Owner owner;

    @Column(name = "thumbnail", length=200)
    private String thumbnail;
    
    public Game() {
    }

    public Game(Long id, @NotBlank(message = "Name field cannot be empty") String name, Console console,
            @NotBlank(message = "Owner name field cannot be empty") Owner owner, String thumbnail) {
        this.id = id;
        this.name = name;
        this.console = console;
        this.owner = owner;
        this.thumbnail = thumbnail;
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

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((console == null) ? 0 : console.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((thumbnail == null) ? 0 : thumbnail.hashCode());
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
        if (console != other.console)
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (thumbnail == null) {
            if (other.thumbnail != null)
                return false;
        } else if (!thumbnail.equals(other.thumbnail))
            return false;
        return true;
    }
}
