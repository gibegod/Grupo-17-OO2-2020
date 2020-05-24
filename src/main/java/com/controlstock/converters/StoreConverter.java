package com.controlstock.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.controlstock.entities.Batch;
import com.controlstock.entities.Employee;
import com.controlstock.entities.Store;
import com.controlstock.models.BatchModel;
import com.controlstock.models.EmployeeModel;
import com.controlstock.models.StoreModel;

@Component("storeConverter")
public class StoreConverter {
	
	@Autowired
	@Qualifier("addressConverter")
	private AddressConverter addressConverter;
	
	@Autowired
	@Qualifier("employeeConverter")
	private EmployeeConverter employeeConverter;
	
	@Autowired
	@Qualifier("batchConverter")
	private BatchConverter batchConverter;
	
	
	public StoreModel entityToModel(Store store) {
		return new StoreModel(store.getId(), addressConverter.entityToModel(store.getAddress()),  
							store.getPhoneNumber(), entityToModelSetEmployee(store.getSetEmployees()), entityToModelSetBatch(store.getSetBatchs()));
	}
	
	public Store modelToEntity(StoreModel storeModel) {
		return new Store(storeModel.getId(), addressConverter.modelToEntity(storeModel.getAddress()),  
						storeModel.getPhoneNumber(), modelToEntitySetEmployee(storeModel.getSetEmployees()), modelToEntitySetBatch(storeModel.getSetBatchs())); 
	}
	
	
	public Set<EmployeeModel> entityToModelSetEmployee(Set<Employee> setEmployees) {
		Set<EmployeeModel> setEmployeesModel = new HashSet<EmployeeModel>();
		
		for(Employee e : setEmployees) {
			EmployeeModel employeeM = employeeConverter.entityToModelSetEmployee(e);
			setEmployeesModel.add(employeeM);
		}

		return setEmployeesModel;
	}
	
	public Set<Employee> modelToEntitySetEmployee(Set<EmployeeModel> setEmployees) {
		Set<Employee> setEmployeesEntity = new HashSet<Employee>();
		
		if (setEmployees == null) {
			return setEmployeesEntity;
		} else {
			for(EmployeeModel e : setEmployees) {
				Employee employee = employeeConverter.modelToEntitySetEmployee(e);
				setEmployeesEntity.add(employee);
			}
		}

		return setEmployeesEntity;
	}
	
	public Set<BatchModel> entityToModelSetBatch(Set<Batch> setBatchs){
		Set<BatchModel> setBatchsModel = new HashSet<BatchModel>();
		
		for(Batch b : setBatchs) {
			BatchModel batchM = batchConverter.entityToModelSetBatch(b);
			setBatchsModel.add(batchM);
		}
		return setBatchsModel;
	}
	
	public Set<Batch> modelToEntitySetBatch(Set<BatchModel> setBatchs){
		Set<Batch> setBatchsE = new HashSet<Batch>();
		if (setBatchs == null) {
			return setBatchsE;
		}
		else {
		for(BatchModel b : setBatchs) {
			Batch batchM = batchConverter.modelToEntitySetBatch(b);
			setBatchsE.add(batchM);
			}
		}
		return setBatchsE;
	}
	
	
	
}