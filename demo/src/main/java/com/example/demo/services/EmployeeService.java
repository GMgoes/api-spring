package com.example.demo.services;

import java.util.List;
import com.example.demo.models.Employee;
import org.springframework.stereotype.Service;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee createEmployee(Employee newEmp) {
        return repository.save(newEmp);
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    public Employee updateEmployee(Long id, Employee updEmployee) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(updEmployee.getName());
                    employee.setRole(updEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    updEmployee.setId(id);
                    return repository.save(updEmployee);
                });
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}