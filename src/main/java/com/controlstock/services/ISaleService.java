package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.Sale;
import com.controlstock.models.SaleModel;
import com.controlstock.models.SaleRequestModel;

public interface ISaleService {
	
	public List<Sale> getAll();
	
	public SaleModel findById(int id);
	
	public SaleModel insert(SaleModel saleModel);
	
	public SaleModel update(SaleModel saleModel);
	
	public void updateStatus(SaleModel saleModel);
	
	public boolean remove(int id);
	
	public List<Sale> getSaleListByStatus();
	
	public Sale getSaleByStatus();
	
	public float calculateTotal(int id);

	public void checkSales(List<Sale> salesList);

}
