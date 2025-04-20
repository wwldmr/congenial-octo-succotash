package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);
    EmployeeDto getEmployeeByEmail(String email);

}
