package com.controlstock.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private int number;
	
	@NotNull
	@OneToMany
	private Set<SaleRequest> setSaleRequests;
	
	@NotNull
	@OneToOne
	private Employee employeeInCharge;
	
	@NotNull
	@OneToOne
	private Client client;
	
	@NotNull
	private LocalDate date;
	
	@NotNull
	private LocalTime time;
	
	@NotNull
	private float totalPrice;
	
	public Sale(){};
	
	public Sale(int number, Employee employeeInCharge, Client client, LocalDate date,
			LocalTime time) {
		super();
		this.number = number;
		this.setSaleRequests = new HashSet<SaleRequest>();
		this.employeeInCharge = employeeInCharge;
		this.client = client;
		this.date = date;
		this.time = time;
		this.totalPrice = 0;
	}
	
	
	
	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}



	public Set<SaleRequest> getSetSaleRequests() {
		return setSaleRequests;
	}



	public void setSetSaleRequests(Set<SaleRequest> setSaleRequests) {
		this.setSaleRequests = setSaleRequests;
	}



	public Employee getEmployeeInCharge() {
		return employeeInCharge;
	}



	public void setEmployeeInCharge(Employee employeeInCharge) {
		this.employeeInCharge = employeeInCharge;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public LocalTime getTime() {
		return time;
	}



	public void setTime(LocalTime time) {
		this.time = time;
	}



	public float getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}



	public int getId() {
		return id;
	}



	@Override
	public String toString() {
		return "Sale [id=" + id + ", number=" + number + ", setSaleRequests=" + setSaleRequests + ", employeeInCharge="
				+ employeeInCharge + ", client=" + client + ", date=" + date + ", time=" + time + ", totalPrice="
				+ totalPrice + "]";
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
	public boolean generarPedidoConStockPropio(Product producto, int cantidad) {
		this.vendedorEncargado.getSucursal().restarLotes(producto, cantidad);
		
		float plus=((producto.getUnitPrice()*5)/100)*cantidad;
		
		this.vendedorEncargado.setPlus(this.vendedorEncargado.getPlus()+plus);
		return lstPedidos.add(new Pedido(producto, cantidad,null));
	}
	
	/*************************************************************/
	/*
	public void calcularTotal() {
		for(int i=0; i<lstPedidos.size(); i++) {
			this.setPrecioTotal(precioTotal+(lstPedidos.get(i).getCantidad() * lstPedidos.get(i).getProducto().getUnitPrice()));
		}
	}
	*/
}
