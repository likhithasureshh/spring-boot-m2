package com.module_2.PresentationLayer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long empId;
    private String empName;
    private Integer empAge;
    private LocalDate dateOfJoining;
    private Boolean isActive;

}
