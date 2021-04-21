package com.cognizant.springlearn.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

@Service
public class EmployeeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	
	private ApplicationContext context;
	List<Employee> list;

	
	/*
	 * public EmployeeService() { context = new
	 * ClassPathXmlApplicationContext("employee.xml"); list =
	 * context.getBean("employees", java.util.ArrayList.class); }
	 */




	public List<Employee> getAllEmployees() {
		LOGGER.info("Start");

		List<Employee> employees = new EmployeeDao().getAllEmployees();

		LOGGER.info("End");

		return employees;
	}

	public void deleteEmployee(int id) throws EmployeeNotFoundException {
		new EmployeeDao().deleteEmployee(id);
	}
	
	
	public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
		LOGGER.info("Start");

		new EmployeeDao().updateEmployee(employee);

		LOGGER.info("End");
	}
	
	

}
