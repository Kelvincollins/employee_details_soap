package com.example.employee_details_soap.repository;

import java.util.List;

import com.example.employee_details_soap.entity.EmployeeModel;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository CrudRepository<EmployeeModel, Long> {
	EmployeeModel findByEmployeeId(long employeeId);
    List<EmployeeModel> findByFirstNameAndSecondName(String firstName, String secondName);

}
