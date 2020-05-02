package com.controlstock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.ProductoConverter;
import com.controlstock.entities.Producto;
import com.controlstock.models.ProductoModel;
import com.controlstock.repositories.IProductoRepository;
import com.controlstock.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService {
	
	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;
	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	
	@Override
	public List<Producto> getAll() {
		return productoRepository.findAll();
	}
	
	@Override
	public ProductoModel insertOrUpdate(ProductoModel productoModel) {
		Producto producto = productoRepository.save(productoConverter.modelToEntity(productoModel));
		return productoConverter.entityToModel(producto);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			productoRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public ProductoModel findById(int id) {
		return productoConverter.entityToModel(productoRepository.findById(id));
	}
	
}