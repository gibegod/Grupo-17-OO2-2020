package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.Sucursal;
import com.controlstock.models.StoreModel;

public interface IStoreService {
	
	public List<Sucursal> getAll();
	
	public StoreModel findById(int id);
	
	public StoreModel insertOrUpdate(StoreModel storeModel);
	
	public boolean remove(int id);

}
