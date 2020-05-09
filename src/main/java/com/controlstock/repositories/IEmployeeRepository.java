package com.controlstock.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.controlstock.entities.Employee;

@Repository("employeeRepository")
public interface IEmployeeRepository extends JpaRepository<Employee, Serializable> {

	public abstract Employee findById(int id);
}
