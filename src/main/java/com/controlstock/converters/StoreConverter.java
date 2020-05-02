package com.controlstock.converters;

import org.springframework.stereotype.Component;
import com.controlstock.entities.Sucursal;
import com.controlstock.models.StoreModel;

@Component("storeConverter")
public class StoreConverter {
	
	public StoreModel entityToModel(Sucursal store) {
		return new StoreModel(store.getUbicacion(),store.getId(),  store.getTelefono(), store.getGerente());
	}
	
	public Sucursal modelToEntity(StoreModel storeModel) {
		return new Sucursal( storeModel.getUbicacion(), storeModel.getId(), storeModel.getTelefono(), storeModel.getGerente());
	}
	

}
