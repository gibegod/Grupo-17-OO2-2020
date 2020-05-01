package com.controlstock.converters;

import org.springframework.stereotype.Component;

import com.controlstock.entities.Producto;
import com.controlstock.models.ProductoModel;

/* Descripcion: Pasa de model a entity y de entity a model. */

@Component("productoConverter")
public class ProductoConverter {
	
	public ProductoModel entityToModel(Producto producto) {
		return new ProductoModel(producto.getId(), producto.isStatus(), producto.getDescripcion(),
								producto.getPrecioUnitario()/*, producto.getFechaAlta()*/, producto.getMarca());
	}
	
	public Producto modelToEntity(ProductoModel productoModel) {
		return new Producto(productoModel.getId(), productoModel.isStatus(), productoModel.getDescripcion(),
							productoModel.getPrecioUnitario()/*, productoModel.getFechaAlta()*/, productoModel.getMarca());
	}
	
}
