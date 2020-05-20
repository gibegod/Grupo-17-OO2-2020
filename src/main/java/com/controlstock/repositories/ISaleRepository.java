package com.controlstock.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlstock.entities.Sale;

@Repository("saleRepository")
public interface ISaleRepository extends JpaRepository<Sale, Serializable> {
	
	public abstract Sale findById(int id);
	
}
