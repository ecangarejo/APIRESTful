package com.APIRESTful.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    @NotBlank(message = "El nombre no debe ser nulo")
    private String name;
    @NotBlank(message = "La fecha no debe ser nula")
    private LocalDate creationDate;
    private DepartmentDTO departmentDTO;

}
