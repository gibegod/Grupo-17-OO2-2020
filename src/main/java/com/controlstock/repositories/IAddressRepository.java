package com.controlstock.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlstock.entities.Address;

@Repository("addressRepository")
public interface IAddressRepository extends JpaRepository<Address, Serializable> {
	
	public abstract Address findById(int id);
	
}
