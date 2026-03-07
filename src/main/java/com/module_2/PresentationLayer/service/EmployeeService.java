package com.module_2.PresentationLayer.service;

import com.module_2.PresentationLayer.dtos.EmployeeDTO;
import com.module_2.PresentationLayer.entity.Employee;
import com.module_2.PresentationLayer.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    public EmployeeDTO getEmployeeById(Long id)
    {
       Employee employee = employeeRepository.findById(id).orElse(null);
       return modelMapper.map(employee,EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees()
    {
        List<Employee> employees= employeeRepository.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO)
    {
        Employee employee = employeeRepository.save(modelMapper.map(employeeDTO,Employee.class));
        return modelMapper.map(employee,EmployeeDTO.class);
    }
}
