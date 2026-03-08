package com.module_2.PresentationLayer.dtos;

import com.module_2.PresentationLayer.validations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long empId;

    @NotNull(message = "Employee name cannot be null")
    @NotEmpty(message = "Employee name should not be empty")
    @NotBlank(message = "Employee name should not be blank")
    @Size(min = 3, max = 10, message = "The name of the employee should be in the range [3,10]")
    private String empName;

    @Email
    private String empEmail;
//    @Pattern(regexp = "^(USER|ADMIN)$",message = "Role should be either ADMIN or USER")
    @EmployeeRoleValidation
    private String role;
    @Min(value = 18, message = "The min value is 18")
    @Max(value = 80, message = "The max value is 80")
    @Positive(message = "only positive values are allowed for age")
    private Integer empAge;
    @Digits(integer = 6,fraction = 2)
    @DecimalMin(value = "100.50")
    @DecimalMax(value = "100000.99")
    private Double salary;
    @PastOrPresent(message = "the dateOfJoining should be either in past or present")
    private LocalDate dateOfJoining;
    @AssertTrue
    private Boolean isActive;

}
