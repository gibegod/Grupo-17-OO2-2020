package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.Producto;
import com.controlstock.models.ProductoModel;

public interface IProductoService {
	
	public List<Producto> getAll();
	
	public ProductoModel findById(int id);
	
	public ProductoModel insertOrUpdate(ProductoModel productoModel);
	
	public boolean remove(int id);

}
