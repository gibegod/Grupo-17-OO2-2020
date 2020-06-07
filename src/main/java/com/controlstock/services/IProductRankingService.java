package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.ProductRanking;
import com.controlstock.models.ProductRankingModel;

public interface IProductRankingService {

	public List<ProductRanking> getAll();
	
	public ProductRankingModel insertOrUpdate(ProductRankingModel product);
}
