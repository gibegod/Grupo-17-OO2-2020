package com.controlstock.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlstock.entities.SaleRequest;

@Repository("saleRequestRepository")
public interface ISaleRequestRepository extends JpaRepository<SaleRequest, Serializable> {
	
	public abstract SaleRequest findById(int id);
	
}