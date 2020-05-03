package com.controlstock.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlstock.entities.Product;

@Repository("productRepository")
public interface IProductRepository extends JpaRepository<Product, Serializable> {
	
	public abstract Product findById(int id);
	
}
