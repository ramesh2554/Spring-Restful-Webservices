package com.cognizant.springlearn.dao;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

public class EmployeeDao {
	
	private static ArrayList<Employee> EMPLOYEE_LIST;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

	
	public EmployeeDao() {
		super();
		ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
		this.setEMPLOYEE_LIST((ArrayList<Employee>)context.getBean("employees"));


	}

	public static ArrayList<Employee> getAllEmployees() {
		return EMPLOYEE_LIST;
	}

	public static void setEMPLOYEE_LIST(ArrayList<Employee> eMPLOYEE_LIST) {
		EMPLOYEE_LIST = eMPLOYEE_LIST;
	}
	

	

	public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
		LOGGER.info("Start");
		
		boolean updated = false;
		for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {

			if (EMPLOYEE_LIST.get(i).getId() == employee.getId()) {
				EMPLOYEE_LIST.set(i, employee);
				updated = true;
			}
		}

		if (!updated)
			throw new EmployeeNotFoundException();

		LOGGER.info("End");
	}

	public void deleteEmployee(int id) throws EmployeeNotFoundException {
		LOGGER.info("Start");
		
		boolean deleted = false;
		for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {

			if (EMPLOYEE_LIST.get(i).getId() == id) {
				EMPLOYEE_LIST.remove(i);
				deleted = true;
			}
		}

		if (!deleted)
			throw new EmployeeNotFoundException();

		LOGGER.info("End");
	}
}
