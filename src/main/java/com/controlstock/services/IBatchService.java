package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.Batch;
import com.controlstock.models.BatchModel;

public interface IBatchService {
	
	public List<Batch> getAll();
	
	public BatchModel findById(int id);
	
	public BatchModel insert(BatchModel batchModel);
	
	public BatchModel update(BatchModel batchModel);
	
	public boolean remove(int id);

}
