package com.controlstock.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.controlstock.entities.Client;

@Repository("clientRepository")
public interface IClientRepository extends JpaRepository<Client, Serializable> {

	public abstract Client findById(int id);
}
