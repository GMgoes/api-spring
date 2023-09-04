package com.example.demo.services;

import java.util.List;
import com.example.demo.models.Owner;
import org.springframework.stereotype.Service;
import com.example.demo.repository.OwnerRepository;

@Service
public class OwnerService {

    private final OwnerRepository repository;

    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public List<Owner> getAllOwner() {
        return repository.findAll();
    }

    public Owner createOwner(Owner newEmp) {
        // TODO: Falta fazer
        return repository.save(newEmp);
    }

    public Owner getOwnerById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    public Owner updateOwner(Long id, Owner updOwner) {
        // TODO: Falta fazer
        return repository.findById(id)
                .orElseThrow();
    }

    public void deleteOwner(Long id) {
        repository.deleteById(id);
    }
}