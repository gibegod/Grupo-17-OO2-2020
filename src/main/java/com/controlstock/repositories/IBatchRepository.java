package com.controlstock.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlstock.entities.Batch;

@Repository("batchRepository")
public interface IBatchRepository extends JpaRepository<Batch, Serializable> {
	
	public abstract Batch findById(int id);
	
}
