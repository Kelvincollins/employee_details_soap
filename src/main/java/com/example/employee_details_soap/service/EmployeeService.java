package com.example.employee_details_soap.service;

import java.util.List;

import com.example.employee_details_soap.entity.EmployeeModel;


public interface EmployeeService {
     List<EmployeeModel> getAllEmployees();
     EmployeeModel getEmployeeById(long employeeId);
     boolean addEmployee(EmployeeModel employee);
     void updateEmployee(EmployeeModel employee);
     void deleteEmployee(EmployeeModel employee);
} 