package com.example.employee_details_soap.service;

import java.util.List;

import com.example.employee_details_soap.entity.EmployeeModel;
import com.example.employee_details_soap.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeModel getEmployeeById(long employeeId) {
		EmployeeModel obj = employeeRepository.findByEmployeeId(employeeId);
		return obj;
	}	
	@Override
	public List<EmployeeModel> getAllEmployees(){
		List<EmployeeModel> list = new ArrayList<>();
		employeeRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addEmployee(EmployeeModel employee){
	        List<EmployeeModel> list = employeeRepository.findByFirstNameAndLastName(employee.getFirstName(), employee.getSecondName()); 	
                if (list.size() > 0) {
    	           return false;
                } else {
                	employee = employeeRepository.save(employee);
    	           return true;
                }
	}
	@Override
	public void updateEmployee(EmployeeModel employee) {
		employeeRepository.save(employee);
	}
	@Override
	public void deleteEmployee(EmployeeModel employee) {
		employeeRepository.delete(employee);
	}
} 

}
