package com.module_2.PresentationLayer.controllers;

import com.module_2.PresentationLayer.dtos.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping("/{empId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "empId") Long id)
    {
        return new EmployeeDTO(id,"Likitha",22, LocalDate.of(2025,3,1),true);
    }

    @GetMapping
    public String getData(@RequestParam(required = false) Integer age,
                          @RequestParam(required = false) String sortBy)
    {
        return "Hi dear,your age is: "+age+"we are sorting by: "+sortBy;
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        employeeDTO.setEmpId(100L);
        return employeeDTO;
    }

}
