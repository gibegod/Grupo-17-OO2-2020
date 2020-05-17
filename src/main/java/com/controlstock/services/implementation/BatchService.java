package com.controlstock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.BatchConverter;
import com.controlstock.converters.ProductConverter;
import com.controlstock.entities.Batch;
import com.controlstock.entities.Product;
import com.controlstock.models.BatchModel;
import com.controlstock.models.ProductModel;
import com.controlstock.repositories.IBatchRepository;
import com.controlstock.repositories.IProductRepository;
import com.controlstock.services.IBatchService;

@Service("batchService")
public class BatchService implements IBatchService {
	
	@Autowired
	@Qualifier("batchRepository")
	private IBatchRepository batchRepository;
	
	@Autowired
	@Qualifier("batchConverter")
	private BatchConverter batchConverter;
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@Autowired
	@Qualifier("productRepository")
	private IProductRepository productRepository;
	
	@Autowired
	@Qualifier("productConverter")
	private ProductConverter productConverter;
	
	@Override
	public List<Batch> getAll() {
		return batchRepository.findAll();
	}
	
	@Override
	public BatchModel insert(BatchModel batchModel) {
		
		Product product = productRepository.findById(batchModel.getProduct().getId());
		ProductModel productModel = productConverter.entityToModel(product);
		batchModel.setProduct(productModel);
		
		Batch batch = batchRepository.save(batchConverter.modelToEntity(batchModel));
		return batchConverter.entityToModel(batch);
	}
	
	@Override
	public BatchModel update(BatchModel batchModel) {
		batchModel.setProduct(productService.findById(batchModel.getProduct().getId()));
		Batch batch = batchRepository.save(batchConverter.modelToEntity(batchModel));
		return batchConverter.entityToModel(batch);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			batchRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public BatchModel findById(int id) {
		return batchConverter.entityToModel(batchRepository.findById(id));
	}
	
}