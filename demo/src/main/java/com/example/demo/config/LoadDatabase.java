package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.models.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.context.annotation.Configuration;


@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository){
        return args -> {
        log.info("Preloading " + repository.save(new Employee("John Doe", "Developer")));
        log.info("Preloading " + repository.save(new Employee("Jane Doe", "UX/UI")));
        };
    }
}
