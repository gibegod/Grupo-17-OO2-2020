package com.controlstock.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

//Lote
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Batch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Product product;
	
	@NotNull
	private String size;
	
	@NotNull
	private int currentAmount;
	
	@NotNull
	private int initialAmount;
	
	@NotNull
	private LocalDate admissionDate;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Store store;
	
	public Batch() {}
	
	public Batch(int id, Product product, String size, int currentAmount, int initialAmount, LocalDate admissionDate, Store store) {
		super();
		this.id = id;
		this.product = product;
		this.size = size;
		this.currentAmount = initialAmount;
		this.initialAmount = initialAmount;
		this.admissionDate = admissionDate;
		this.store=store;
	}
	
	public Batch(int id, Product product, String size, int currentAmount, int initialAmount, LocalDate admissionDate) {
		super();
		this.id = id;
		this.product = product;
		this.size = size;
		this.currentAmount = initialAmount;
		this.initialAmount = initialAmount;
		this.admissionDate = admissionDate;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public int getCurrentAmount() {
		return currentAmount;
	}


	public void setCurrentAmount(int currentAmount) {
		this.currentAmount = currentAmount;
	}


	public int getInitialAmount() {
		return initialAmount;
	}


	public void setInitialAmount(int initialAmount) {
		this.initialAmount = initialAmount;
	}


	public LocalDate getAdmissionDate() {
		return admissionDate;
	}


	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	

}