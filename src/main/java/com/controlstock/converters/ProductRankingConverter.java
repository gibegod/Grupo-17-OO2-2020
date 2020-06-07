package com.controlstock.converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.controlstock.entities.ProductRanking;
import com.controlstock.models.ProductRankingModel;

@Component("productRankingConverter")
public class ProductRankingConverter {
	
	@Autowired
	@Qualifier("productConverter")
	private ProductConverter productConverter;

	public ProductRankingModel entityToModel(ProductRanking product) {
		return new ProductRankingModel(product.getId(), productConverter.entityToModel(product.getProduct()), product.getAmount());
	}
	
	public ProductRanking modelToEntity(ProductRankingModel productModel) {
		return new ProductRanking(productModel.getId(), productConverter.modelToEntity(productModel.getProduct()), productModel.getAmount());
	}
}
