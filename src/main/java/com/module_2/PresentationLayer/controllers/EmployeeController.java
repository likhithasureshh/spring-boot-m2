package com.module_2.PresentationLayer.controllers;

import com.module_2.PresentationLayer.dtos.EmployeeDTO;
import com.module_2.PresentationLayer.entity.Employee;

import com.module_2.PresentationLayer.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;
    @GetMapping("/{empId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "empId") Long id)
    {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees()
    {
        return employeeService.getAllEmployees();
    }


    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        return employeeService.createNewEmployee(employeeDTO);
    }

}
