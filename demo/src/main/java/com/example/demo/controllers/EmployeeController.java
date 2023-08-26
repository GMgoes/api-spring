package com.example.demo.controllers;

import java.util.List;
import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> allEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmp) {
        return employeeService.createEmployee(newEmp);
    }

    @GetMapping("/employees/{id}")
    public Employee oneEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updEmployee) {
        return employeeService.updateEmployee(id, updEmployee);
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}