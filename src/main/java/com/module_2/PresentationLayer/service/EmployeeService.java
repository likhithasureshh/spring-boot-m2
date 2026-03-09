package com.module_2.PresentationLayer.service;

import com.module_2.PresentationLayer.advices.ResourceNotFoundException;
import com.module_2.PresentationLayer.dtos.EmployeeDTO;
import com.module_2.PresentationLayer.entity.Employee;
import com.module_2.PresentationLayer.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeDTO getEmployeeById(Long empId)
    {
        Employee employee = employeeRepository.findById(empId).orElseThrow(()-> new ResourceNotFoundException("Resource is not found with id: "+empId));
        return modelMapper.map(employee,EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees()
    {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO)
    {
         Employee savedEmployee = employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
         return modelMapper.map(savedEmployee,EmployeeDTO.class);

    }

    public EmployeeDTO updateEntireEmployee(EmployeeDTO employeeDTO,Long empId)
    {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee.setEmpId(empId);
        return modelMapper.map(employeeRepository.save(employee),EmployeeDTO.class);
    }

    public void isExists(Long id)
    {
        Boolean exists= employeeRepository.existsById(id);
        if(!exists)
        {
            throw new ResourceNotFoundException("Resource not found with id: "+id);
        }
    }

    public EmployeeDTO updateEmployeeById(Long empId, Map<String, Object> updates)
    {
        isExists(empId);
        Employee employee = employeeRepository.findById(empId).get();
        updates.forEach((field,value)->
        {
            Field field1 = ReflectionUtils.findField(Employee.class,field);
            field1.setAccessible(true);
            ReflectionUtils.setField(field1,employee,value);
        });
        return modelMapper.map(employeeRepository.save(employee),EmployeeDTO.class);

    }

    public Boolean deleteEmployeeById(Long empId)
    {
        isExists(empId);
        employeeRepository.deleteById(empId);
        return true;
    }
}
