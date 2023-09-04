package com.example.demo.repository;

import com.example.demo.models.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long>{

    boolean existsByNameAndPhoneNumber(String name, String phoneNumber);
}