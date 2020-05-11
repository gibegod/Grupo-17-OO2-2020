package com.controlstock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.EmployeeConverter;
import com.controlstock.entities.Employee;
import com.controlstock.models.EmployeeModel;
import com.controlstock.repositories.IEmployeeRepository;
import com.controlstock.services.IEmployeeService;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	@Qualifier("employeeRepository")
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	@Qualifier("employeeConverter")
	private EmployeeConverter employeeConverter;
	
	@Autowired
	@Qualifier("storeService")
	private StoreService storeService;
	
	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}
	
	@Override
	public EmployeeModel insertOrUpdate(EmployeeModel employeeModel) {
		//employeeModel.setStore(StoreService.findById(employeeModel.getStore().getId()));
		Employee employee = employeeRepository.save(employeeConverter.modelToEntity(employeeModel));
		return employeeConverter.entityToModel(employee);
	}
	
	@Override
	public EmployeeModel insert(EmployeeModel employeeModel) {
		Employee employee = employeeRepository.save(employeeConverter.modelToEntity(employeeModel));
		return employeeConverter.entityToModel(employee);
	}
	
	@Override
	public EmployeeModel update(EmployeeModel employeeModel) {
		employeeModel.setStore(storeService.findById(employeeModel.getStore().getId()));
		Employee employee = employeeRepository.save(employeeConverter.modelToEntity(employeeModel));
		return employeeConverter.entityToModel(employee);

	}
	
	@Override
	public boolean remove(int id) {
		try {
			employeeRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public EmployeeModel findById(int id) {
		return employeeConverter.entityToModel(employeeRepository.findById(id));
	}
	
}