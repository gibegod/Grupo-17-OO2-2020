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
import javax.validation.constraints.NotNull;

//Lote
@Entity
public class Batch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Product product;
	
	@NotNull
	private char size;
	
	@NotNull
	private int currentAmount;
	
	@NotNull
	private int initialAmount;
	
	@NotNull
	private LocalDate admissionDate;
	
	public Batch() {};
	public Batch(Product product, char size, int initialAmount, LocalDate admissionDate) {
		super();
		this.product = product;
		this.size = size;
		this.currentAmount = initialAmount;
		this.initialAmount = initialAmount;
		this.admissionDate = admissionDate;
	}


	public int getId() {
		return id;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public char getSize() {
		return size;
	}


	public void setSize(char size) {
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
	
	

}