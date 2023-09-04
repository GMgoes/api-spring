package com.example.demo.controllers;

import java.util.List;
import com.example.demo.models.Owner;
import com.example.demo.services.OwnerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping()
    public List<Owner> allEmployees() {
        return ownerService.getAllOwner();
    }

    @PostMapping()
    public Owner newOwner(@RequestBody Owner newEmp) {
        return ownerService.createOwner(newEmp);
    }

    @GetMapping("/{id}")
    public Owner oneOwner(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    @PutMapping("/{id}")
    public Owner updateOwner(@PathVariable Long id, @RequestBody Owner updOwner) {
        return ownerService.updateOwner(id, updOwner);
    }

    @DeleteMapping("/{id}")
    void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }
}