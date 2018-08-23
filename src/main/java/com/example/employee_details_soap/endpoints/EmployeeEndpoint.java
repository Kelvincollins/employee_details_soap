package com.example.employee_details_soap.endpoints;




import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.employee_details_soap.entity.EmployeeModel;
import com.example.employee_details_soap.service.EmployeeServiceImpl;

@Endpoint
public class EmployeeEndpoint {
	private static final String NAMESPACE_URI = "http://collinsk.com/employee_details_soap/employee-ws";
	@Autowired
	private EmployeeServiceImpl employeeService;	

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
	@ResponsePayload
	public GetEmployeeByIdResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request) {
		GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();
		EmployeeInfo employeeInfo = new employeeInfo();
		BeanUtils.copyProperties(employeeService.getEmployeeById(request.getEmployeeId()), employeeInfo);
		response.setEmployeeInfo(employeeInfo);
		return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEmployeeRequest")
	@ResponsePayload
	public GetAllEmployeesResponse getAllEmployee() {
		GetAllEmployeesResponse response = new GetAllEmployeesResponse();
		List<EmployeeInfo> employeeInfoList = new ArrayList<>();
		List<EmployeeModel> employeeList = employeeService.getAllEmployees();
		for (int i = 0; i < employeeList.size(); i++) {
			EmployeeInfo ob = new EmployeeInfo();
		     BeanUtils.copyProperties(employeeList.get(i), ob);
		     employeeInfoList.add(ob);    
		}
		response.getEmployeeInfo().addAll(employeeInfoList);
		return response;
	}	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
	@ResponsePayload
	public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
		AddEmployeeResponse response = new AddEmployeeResponse();		
    	        ServiceStatus serviceStatus = new ServiceStatus();		
    	        EmployeeModel employee = new EmployeeModel();
		employee.setFirstName(request.getFirstName());
		article.setSecondName(request.getSecondName());		
                boolean flag = employeeService.addEmployee(employee);
                if (flag == false) {
        	   serviceStatus.setStatusCode("CONFLICT");
        	   serviceStatus.setMessage("Content Already Available");
        	   response.setServiceStatus(serviceStatus);
                } else {
                	EmployeeInfo employeeInfo = new EmployeeInfo();
	           BeanUtils.copyProperties(Employee, employeeInfo);
		   response.setEmployeeInfo(employeeInfo);
        	   serviceStatus.setStatusCode("SUCCESS");
        	   serviceStatus.setMessage("Content Added Successfully");
        	   response.setServiceStatus(serviceStatus);
                }
                return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
	@ResponsePayload
	public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
		EmployeeModel employee = new EmployeeModel();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
		employeeService.updateArticle(employee);
    	        ServiceStatus serviceStatus = new ServiceStatus();
    	        serviceStatus.setStatusCode("SUCCESS");
    	        serviceStatus.setMessage("Content Updated Successfully");
    	        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
    	        response.setServiceStatus(serviceStatus);
    	        return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
	@ResponsePayload
	public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
		EmployeeModel employee = employeeService.getEmployeeById(request.getEmployeeId());
    	        ServiceStatus serviceStatus = new ServiceStatus();
		if (Employee == null ) {
	    	    serviceStatus.setStatusCode("FAIL");
	    	    serviceStatus.setMessage("Content Not Available");
		} else {
			employeeService.deleteEmployee(Employee);
	    	    serviceStatus.setStatusCode("SUCCESS");
	    	    serviceStatus.setMessage("Content Deleted Successfully");
		}
    	        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
    	        response.setServiceStatus(serviceStatus);
		return response;
	}	
} 