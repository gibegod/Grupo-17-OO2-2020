package com.controlstock.converters;

import org.springframework.stereotype.Component;
import com.controlstock.entities.Store;
import com.controlstock.models.StoreModel;

@Component("storeConverter")
public class StoreConverter {
	
	public StoreModel entityToModel(Store store) {
		return new StoreModel(store.getUbicacion(),store.getId(),  store.getTelefono(), store.getGerente());
	}
	
	public Store modelToEntity(StoreModel storeModel) {
		return new Store( storeModel.getUbicacion(), storeModel.getId(), storeModel.getTelefono(), storeModel.getGerente());
	}
	

}
