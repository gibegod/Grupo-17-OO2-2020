package com.controlstock.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.controlstock.entities.Employee;
import com.controlstock.models.EmployeeModel;

@Component("employeeConverter")
public class EmployeeConverter {

	@Autowired
	@Qualifier("storeConverter")
	private StoreConverter storeConverter;
	
	public EmployeeModel entityToModel(Employee employee) {
		return new EmployeeModel(employee.getId(), employee.getName(), employee.getSurname(), employee.getBirthdate(),
								employee.getDni(), employee.getWorkingHours(), employee.isManager(), 
								employee.getMinimunWage(), employee.getPlus(), storeConverter.entityToModel(employee.getStore()));
	}
	
	
	
	public Employee modelToEntity(EmployeeModel employeeModel) {
		return new Employee(employeeModel.getId(), employeeModel.getName(), employeeModel.getSurname(), 
							employeeModel.getBirthdate(),
							employeeModel.getDni(), employeeModel.getWorkingHours(), employeeModel.isManager(), 
							employeeModel.getMinimunWage(), employeeModel.getPlus(), storeConverter.modelToEntity(employeeModel.getStore()));
	}

}