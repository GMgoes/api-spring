package com.example.demo.models;

import java.util.List;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Owner")
public class Owner {

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    private Long id;

    @NotBlank(message = "Name field cannot be empty")
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @NotBlank(message = "Phone Number field cannot be empty")
    @Column(name = "phone_number", length = 60, nullable = false)
    private String phone_number;

    @Column(name = "games")
    private List<Game> games;

    public Owner() {
    }

    public Owner(Long id, @NotBlank(message = "Name field cannot be empty") String name,
            @NotBlank(message = "Phone Number field cannot be empty") String phone_number, List<Game> games) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.games = games;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((phone_number == null) ? 0 : phone_number.hashCode());
        result = prime * result + ((games == null) ? 0 : games.hashCode());
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
        Owner other = (Owner) obj;
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
        if (phone_number == null) {
            if (other.phone_number != null)
                return false;
        } else if (!phone_number.equals(other.phone_number))
            return false;
        if (games == null) {
            if (other.games != null)
                return false;
        } else if (!games.equals(other.games))
            return false;
        return true;
    }
}
