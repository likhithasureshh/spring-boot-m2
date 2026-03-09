package com.module_2.PresentationLayer.controllers;

import com.module_2.PresentationLayer.dtos.EmployeeDTO;
import com.module_2.PresentationLayer.entity.Employee;
import com.module_2.PresentationLayer.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    @GetMapping(path = "/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long empId)
    {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(empId);
        return ResponseEntity.ok(employeeDTO);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees()
    {
        List<EmployeeDTO> employeeDTOS = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDTOS);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO)
    {
        EmployeeDTO employeeDTO1 = employeeService.createNewEmployee(employeeDTO);
        return new ResponseEntity<>(employeeDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> updateEntireEmployeeById(@RequestBody EmployeeDTO employeeDTO,@PathVariable Long empId)
    {
        EmployeeDTO employeeDTO1 = employeeService.updateEntireEmployee(employeeDTO,empId);
        return ResponseEntity.ok(employeeDTO1);
    }

    @PatchMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable Long empId, @RequestBody Map<String,Object> updates)
    {
        EmployeeDTO employeeDTO = employeeService.updateEmployeeById(empId,updates);
        if(employeeDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

    @DeleteMapping(path = "/{empId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long empId)
    {
        Boolean result  = employeeService.deleteEmployeeById(empId);
        if(!result) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }


}
