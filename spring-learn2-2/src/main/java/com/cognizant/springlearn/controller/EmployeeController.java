package com.cognizant.springlearn.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

@RestController
public class EmployeeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
//localhost:8888/employees
	@GetMapping("/employees")
	public ArrayList<Employee> getAllEmployees() {

		LOGGER.info("Inside getAllEmployees");
		LOGGER.debug("Employees : {}", employeeService.getAllEmployees());
		LOGGER.info("End");

		return (ArrayList<Employee>) employeeService.getAllEmployees();
	}

	// Put Mapping json
	/*
	 *   {
        "id": 1,
        "name": "employee1",
        "salary": 30000.0,
        "permanent": true,
        "dateOfBirth": "31-12-2019",
        "department": {
            "id": 1,
            "name": "Dept1"
        }
    }
	 */

	@PutMapping("/update")
	public Employee updateEmployees(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {
		LOGGER.info("Start");

		employeeService.updateEmployee(employee);
		LOGGER.debug("Updated Employee: {}", employee);
		LOGGER.info("End");
		return employee;
	}
	
	/*
	 * @PutMapping("/updateCountry") 
	 * public String updateCountry(@RequestBody Country c) throws CountryNotFoundException
	 *  { return cs.updateCountry(c); }
	 */

	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException {
		LOGGER.info("Start");

		employeeService.deleteEmployee(id);
		LOGGER.debug("Deleted Employee: {}", id);

		LOGGER.info("End");
		return "Employee with id " + id + " is deleted";
	}

}
