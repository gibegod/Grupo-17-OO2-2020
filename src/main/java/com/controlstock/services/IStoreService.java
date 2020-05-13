package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.Store;
import com.controlstock.models.StoreModel;

public interface IStoreService {
	
	public List<Store> getAll();
	
	public StoreModel findById(int id);
	
	public StoreModel insertOrUpdate(StoreModel storeModel);
	
	public boolean remove(int id);

	public StoreModel insert(StoreModel storeModel);

	public StoreModel update(StoreModel storeModel);

}
