package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.Sale;
import com.controlstock.models.SaleModel;

public interface ISaleService {
	
	public List<Sale> getAll();
	
	public SaleModel findById(int id);
	
	public SaleModel insert(SaleModel saleModel);
	
	public SaleModel update(SaleModel saleModel);
	
	public boolean remove(int id);

}
