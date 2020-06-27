package com.controlstock.services.implementation;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.ProductRankingConverter;
import com.controlstock.entities.ProductRanking;
import com.controlstock.models.ProductRankingModel;
import com.controlstock.repositories.IProductRankingRepository;
import com.controlstock.services.IProductRankingService;
import com.controlstock.services.IProductService;

@Service("productRankingService")
public class ProductRankingService implements IProductRankingService {

	@Autowired
	@Qualifier("productRankingRepository")
	private IProductRankingRepository productRankingRepository;
	
	@Autowired
	@Qualifier("productRankingConverter")
	private ProductRankingConverter productRankingConverter;
	
	@Autowired
	@Qualifier("productService")
	private IProductService productService;
	
	
	@Override
	public List<ProductRanking> getAll() {
		List<ProductRanking> list = productRankingRepository.findAll();
		//Ordeno la lista de mayor a menor por amount
		list.sort(Comparator.comparing(ProductRanking::getAmount).reversed());
		return list;
	}
	
	@Override
	public ProductRankingModel insert(ProductRankingModel productRankingModel) {
		productRankingModel.setProduct(productService.findById(productRankingModel.getProduct().getId()));
		
		ProductRanking productRanking = productRankingConverter.modelToEntity(productRankingModel);
		productRankingRepository.save(productRanking);
		
		return productRankingConverter.entityToModel(productRanking);
	}
	
	@Override
	public ProductRankingModel update(ProductRankingModel productRankingModel) {
		productRankingModel.setProduct(productService.findById(productRankingModel.getProduct().getId()));
		
		int actualAmount = findById(productRankingModel.getId()).getAmount();
		productRankingModel.setAmount(actualAmount + productRankingModel.getAmount());
		
		ProductRanking productRanking = productRankingConverter.modelToEntity(productRankingModel);
		productRankingRepository.save(productRanking);
		
		return productRankingConverter.entityToModel(productRanking);
	}
	
	@Override
	public ProductRankingModel findById(int id) {
		return productRankingConverter.entityToModel(productRankingRepository.findById(id));
	}

}