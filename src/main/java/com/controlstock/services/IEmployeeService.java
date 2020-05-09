package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.Employee;
import com.controlstock.models.EmployeeModel;

public interface IEmployeeService {
	
	public List<Employee> getAll();
	
	public EmployeeModel findById(int id);
	
	public EmployeeModel insertOrUpdate(EmployeeModel employeeModel);
	
	public boolean remove(int id);

}
