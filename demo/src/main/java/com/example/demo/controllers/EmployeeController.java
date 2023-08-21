package com.example.demo.controllers;

import java.util.List;
import com.example.demo.models.Employee;
import com.example.demo.repository.EmployeeRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    /* public static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Gus") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    } */

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }

    @GetMapping("/employees")
    public List<Employee> allEmployees(){
        return repository.findAll();
    }

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmp){
        return repository.save(newEmp);
    }

    @GetMapping("/employees/{id}")
    public Employee oneEmployee(@PathVariable String id) throws Exception{
        return repository.findById(Long.parseLong(id))
        .orElseThrow(() -> new Exception("Erro"));
    }
        
    @PutMapping(value="/employees/{id}")
    public Employee updateEmployee (@PathVariable String id, @RequestBody Employee updEmployee) {
        return repository.findById(Long.parseLong(id))
      .map(employee -> {
        employee.setName(updEmployee.getName());
        employee.setRole(updEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        updEmployee.setId(Long.parseLong(id));
        return repository.save(updEmployee);
      });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable String id) {
        repository.deleteById(Long.parseLong(id));
    } 
}
