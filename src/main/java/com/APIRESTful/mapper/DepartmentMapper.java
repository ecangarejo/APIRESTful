package com.APIRESTful.mapper;

import com.APIRESTful.dto.DepartmentDTO;
import com.APIRESTful.entities.Department;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {

    DepartmentDTO departmentToDeparmentDTO(Department department);

    @InheritInverseConfiguration
    Department departmentDTOToDepartment(DepartmentDTO departmentDTO);

}
