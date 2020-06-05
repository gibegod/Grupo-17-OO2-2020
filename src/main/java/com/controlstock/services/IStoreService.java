package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.Batch;
import com.controlstock.entities.Product;
import com.controlstock.entities.Store;
import com.controlstock.models.ProductModel;
import com.controlstock.models.StoreModel;

public interface IStoreService {
	
	public int getProductQuantity(int idStore, int idProduct);
	
	public List<Store> getAll();
	
	public List<Product> getProductsByStore(int id);

	public List<Batch> getActiveBatchs(int idStore, int idProduct);
	
	public void substractBatches(int idStore, int idProduct, int quantity);
	
	public boolean validateStock (int idStore, int idProduct, int quantity);
	
	public StoreModel findById(int id);
	
	public StoreModel insertOrUpdate(StoreModel storeModel);
	
	public boolean remove(int id);

	public StoreModel insert(StoreModel storeModel);

	public StoreModel update(StoreModel storeModel);

	public List<Store> getStoresByProductId(int id);
	
	public List<Store> getStoresByStock(int productId, int amount, int saleId);
	
	public float distanceStores(float lat1, float lng1, float lat2, float lng2);

}
