package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;
import org.springframework.stereotype.Service;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);
}
