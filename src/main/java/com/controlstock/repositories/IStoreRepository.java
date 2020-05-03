package com.controlstock.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.controlstock.entities.Store;

@Repository("storeRepository")
public interface IStoreRepository extends JpaRepository<Store, Serializable>{
	
	public abstract Store findById(int id);

}
