package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.Product;
import com.controlstock.models.ProductModel;

public interface IProductService {
	
	public List<Product> getAll();
		
	public ProductModel findById(int id);
	
	public ProductModel insertOrUpdate(ProductModel productModel);
	
	public boolean remove(int id);

}
