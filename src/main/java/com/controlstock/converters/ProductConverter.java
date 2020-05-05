package com.controlstock.converters;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.controlstock.entities.Product;
import com.controlstock.models.ProductModel;

/* Descripcion: Pasa de model a entity y de entity a model. */

@Component("productConverter")
public class ProductConverter {
	
	public ProductModel entityToModel(Product product) {
		return new ProductModel(product.getId(), product.isStatus(), product.getDescription(),
								product.getUnitPrice(), product.getBrand());
	}
	
	public Product modelToEntity(ProductModel productModel) {
		return new Product(productModel.getId(), productModel.isStatus(), productModel.getDescription(),
							productModel.getUnitPrice()/*, productoModel.getFechaAlta()*/, productModel.getBrand());
	}
	
}
