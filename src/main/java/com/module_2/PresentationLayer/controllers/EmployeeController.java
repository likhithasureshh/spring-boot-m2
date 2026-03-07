package com.module_2.PresentationLayer.controllers;

import com.module_2.PresentationLayer.dtos.EmployeeDTO;
import com.module_2.PresentationLayer.entity.Employee;
import com.module_2.PresentationLayer.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/{empId}")
    public Employee getEmployeeById(@PathVariable(name = "empId") Long id)
    {
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }


    @PostMapping
    public Employee createNewEmployee(@RequestBody Employee employee)
    {
        return employeeRepository.save(employee);
    }

}
