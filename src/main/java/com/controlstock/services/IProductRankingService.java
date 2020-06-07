package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.ProductRanking;

public interface IProductRankingService {

	public List<ProductRanking> getAll();
	
	public ProductRanking insertOrUpdate(ProductRanking product);
}
