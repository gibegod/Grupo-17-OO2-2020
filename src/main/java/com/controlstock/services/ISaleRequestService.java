package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.SaleRequest;
import com.controlstock.models.SaleRequestModel;

public interface ISaleRequestService {
	
	public List<SaleRequest> getAll();
	
	public SaleRequestModel findById(int id);
	
	public SaleRequestModel insert(SaleRequestModel batchModel);
	
	public SaleRequestModel update(SaleRequestModel batchModel);
	
	public boolean remove(int id);

}
