package com.controlstock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.ProductConverter;
import com.controlstock.entities.Product;
import com.controlstock.models.ProductModel;
import com.controlstock.repositories.IProductRepository;
import com.controlstock.services.IProductService;

@Service("productService")
public class ProductService implements IProductService {
	
	@Autowired
	@Qualifier("productRepository")
	private IProductRepository productRepository;
	
	@Autowired
	@Qualifier("productConverter")
	private ProductConverter productConverter;
	
	
	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
	@Override
	public ProductModel insertOrUpdate(ProductModel productModel) {
		Product product = productRepository.save(productConverter.modelToEntity(productModel));
		return productConverter.entityToModel(product);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			productRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public ProductModel findById(int id) {
		return productConverter.entityToModel(productRepository.findById(id));
	}
	
}