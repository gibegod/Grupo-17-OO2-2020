package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.ProductRanking;
import com.controlstock.models.ProductRankingModel;

public interface IProductRankingService {

	public List<ProductRanking> getAll();
	
	//public ProductRankingModel insertOrUpdate(ProductRankingModel product);

	public ProductRankingModel insert(ProductRankingModel productRankingModel);
	
	public ProductRankingModel update(ProductRankingModel productRankingModel);

	public ProductRankingModel findById(int id);
}
