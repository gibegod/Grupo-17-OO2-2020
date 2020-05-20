package com.controlstock.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.controlstock.entities.Employee;
import com.controlstock.entities.Store;
import com.controlstock.models.EmployeeModel;
import com.controlstock.models.StoreModel;

@Component("employeeConverter")
public class EmployeeConverter {

	@Autowired
	@Qualifier("storeConverter")
	private StoreConverter storeConverter;
	
	public EmployeeModel entityToModel(Employee employee) {
		return new EmployeeModel(employee.getId(), employee.getName(), employee.getSurname(), employee.getBirthdate(),
								employee.getDni(), employee.getWorkingHours(), employee.isManager(), 
								employee.getMinimunWage(), employee.getPlus(), 
								storeConverter.entityToModel(employee.getStore()));
	}
	
	public Set<EmployeeModel> entityToModelSetEmployee(Set<Employee> setEmployees) {
		
		Set<EmployeeModel> setEmployeesModel = new HashSet<EmployeeModel>();
		
		for(Employee e : setEmployees) {
			setEmployeesModel.add(e);
		}
		
		Store store;
		store.setSetEmployees(setEmployees);
		StoreModel storeModel;
		//storeModel.setSetEmployees(setEmployees);
		Set<EmployeeModel> setEmployeeAux= new HashSet<EmployeeModel>();
		
		store.setSetEmployees(setEmployees);
		storeModel.setSetEmployees(setEmployees);
		
		setEmployeeAux = setEmployees;
		return new HashSet<EmployeeModel>(setEmployees);
	}
	
	
	public Employee modelToEntity(EmployeeModel employeeModel) {
		return new Employee(employeeModel.getId(), employeeModel.getName(), employeeModel.getSurname(), 
							employeeModel.getBirthdate(), employeeModel.getDni(), employeeModel.getWorkingHours(), 
							employeeModel.isManager(), employeeModel.getMinimunWage(), employeeModel.getPlus(), 
							storeConverter.modelToEntity(employeeModel.getStore()));
	}

}