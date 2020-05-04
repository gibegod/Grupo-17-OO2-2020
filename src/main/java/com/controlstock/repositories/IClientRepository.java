package com.controlstock.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.controlstock.entities.Cliente;

@Repository("clientRepository")
public interface IClientRepository extends JpaRepository<Cliente, Serializable> {

	public abstract Cliente findById(int id);
}
