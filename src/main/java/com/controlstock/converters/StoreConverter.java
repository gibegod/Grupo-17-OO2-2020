package com.controlstock.converters;

import org.springframework.stereotype.Component;

import com.controlstock.entities.Address;
import com.controlstock.entities.Employee;
import com.controlstock.entities.Store;
import com.controlstock.models.StoreModel;

@Component("storeConverter")
public class StoreConverter {
	
	public StoreModel entityToModel(Store store) {
		return new StoreModel(store.getAddress(), store.getId(),  store.getPhoneNumber(), store.getManager());
	}
	
	public Store modelToEntity(StoreModel storeModel) {
		return new Store (storeModel.getAddress(), storeModel.getId(), storeModel.getPhoneNumber(), storeModel.getManager()); 
	}
	
}
