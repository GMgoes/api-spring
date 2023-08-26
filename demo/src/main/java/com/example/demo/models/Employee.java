package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Column(name = "name", length = 60, nullable = false)
    private String name;
    @NotBlank(message = "A profissão não pode estar vazia")
    @Column(name = "role", length = 35)
    private String role;

    public Employee(){}

    public Employee(String name, String role){
        this.name = name;
        this.role = role;
    }

    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getRole(){
        return this.role;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setRole(String role){
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
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
        Employee other = (Employee) obj;
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
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Funcionário [id=" + id + ", name=" + name + ", role=" + role + "]";
    }
    
}
