package com.controlstock.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ProductRanking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@OneToOne
	private Product product;
	
	@NotNull
	private int amount;
	
	public ProductRanking(){};
	
	public ProductRanking(Product product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}
	
	
	
	/*@Override
	public String toString() {
		return "\nRankingProductos [producto=" + producto + ", cantidad=" + cantidad + "]";
	}
	*/
	
}