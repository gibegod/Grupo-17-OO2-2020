package com.controlstock.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.controlstock.entities.Store;
import com.controlstock.models.StoreModel;

@Component("storeConverter")
public class StoreConverter {
	
	//@Autowired
	//@Qualifier("addressConverter")
	private AddressConverter addressConverter;
	
	public StoreModel entityToModel(Store store) {
		return new StoreModel(store.getId(), addressConverter.entityToModel(store.getAddress()),  
							store.getPhoneNumber());
	}
	
	public Store modelToEntity(StoreModel storeModel) {
		return new Store (storeModel.getId(), addressConverter.modelToEntity(storeModel.getAddress()),  
						storeModel.getPhoneNumber()); 
	}
	
}
