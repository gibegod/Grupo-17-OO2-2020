package com.controlstock.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.controlstock.entities.Sucursal;

@Repository("storeRepository")

public interface IStoreRepository extends JpaRepository<Sucursal, Serializable>{
	
	public abstract Sucursal findById(int id);

}
